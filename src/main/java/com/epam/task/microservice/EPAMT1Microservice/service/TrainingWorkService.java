package com.epam.task.microservice.EPAMT1Microservice.service;

import com.epam.task.microservice.EPAMT1Microservice.model.DTO.TrainingRequest;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingMonth;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWork;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingYear;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingMonthRepository;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingWorkRepository;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingYearRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainingWorkService {
    private final TrainingWorkRepository trainingWorkRepository;
    private final TrainingYearRepository trainingYearsRepository;
    private final TrainingMonthRepository trainingMonthRepository;

    private static final Logger log = LoggerFactory.getLogger(TrainingWorkService.class);




    public void acceptTrainerWork(TrainingRequest trainingRequest) {
        String transactionId = MDC.get("Transaction-ID");
        log.info("Transaction ID in service: {}", transactionId);
        if ("add".equalsIgnoreCase(trainingRequest.getAction())) {
            log.info("Adding training work for username: {}", trainingRequest.getUsername());
            addTrainingWork(trainingRequest);
            log.info("Added trained work with Transaction ID: {}", transactionId);
        } else {
            log.info("Deleting training work for username: {}", trainingRequest.getUsername());
            deleteTrainingWork(trainingRequest);
            log.info("Deleted trained work with Transaction ID: {}", transactionId);
        }
    }

    public void addTrainingWork(TrainingRequest trainingRequest) {
        if (trainingWorkRepository.findByUsername(trainingRequest.getUsername()).isEmpty()) {
            log.info("Creating new training work entry for username: {}", trainingRequest.getUsername());
            createTrainingWork(trainingRequest);
        } else {
            log.info("Updating existing training work entry for username: {}", trainingRequest.getUsername());
            updateTrainingWork(trainingRequest);
        }
    }

    public void deleteTrainingWork(TrainingRequest trainingRequest) {
        TrainingWork trainingWork = trainingWorkRepository.findByUsername(trainingRequest.getUsername())
                .orElseThrow(() -> {
                    log.error("Training work not found for username: {}", trainingRequest.getUsername());
                    return new NotFoundException("Training work not found");
                });

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(trainingRequest.getDate());
        String currentYear = String.valueOf(calendar.get(Calendar.YEAR));
        String currentMonth = String.valueOf(calendar.get(Calendar.MONTH));

        List<TrainingYear> trainingYears = trainingWork.getYears();
        log.info("Removing training month from year {} and month {} for username: {}", currentYear, currentMonth, trainingRequest.getUsername());
        removeTrainingMonthFromYear(trainingYears, currentYear, currentMonth, trainingRequest);

        if (trainingYears.isEmpty()) {
            log.info("No more training years left. Deleting training work entry for username: {}", trainingRequest.getUsername());
            trainingWorkRepository.delete(trainingWork);
        } else {
            trainingWork.setYears(trainingYears);
            log.info("Updated training work for username: {} with new training years", trainingRequest.getUsername());
            trainingWorkRepository.save(trainingWork);
        }
    }

    private void createTrainingWork(TrainingRequest trainingRequest) {
        TrainingWork trainingWork = new TrainingWork();
        trainingWork.setFirstName(trainingRequest.getFirstName());
        trainingWork.setLastName(trainingRequest.getLastName());
        trainingWork.setActive(trainingRequest.getIsActive());
        trainingWork.setUsername(trainingRequest.getUsername());
        List<TrainingYear> years = List.of(createTrainingYears(trainingRequest));
        trainingWork.setYears(years);
        trainingWorkRepository.save(trainingWork);
        log.info("Training work created for username: {}", trainingRequest.getUsername());
    }

    private void updateTrainingWork(TrainingRequest trainingRequest) {
        TrainingWork trainingWork = trainingWorkRepository.findByUsername(trainingRequest.getUsername()).get();
        List<TrainingYear> years = updateTrainingYears(trainingWork, trainingRequest);
        trainingWork.setYears(years);
        trainingWorkRepository.save(trainingWork);
        log.info("Training work updated for username: {}", trainingRequest.getUsername());
    }

    private TrainingYear createTrainingYears(TrainingRequest trainingRequest) {
        TrainingYear trainingYears = new TrainingYear();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(trainingRequest.getDate());
        trainingYears.setYearNumber(String.valueOf(calendar.get(Calendar.YEAR)));
        List<TrainingMonth> months = List.of(createTrainingMonth(trainingRequest));
        trainingYears.setMonths(months);
        trainingYearsRepository.save(trainingYears);
        log.info("Created new training year for year: {}", trainingYears.getYearNumber());
        return trainingYears;
    }

    private List<TrainingYear> updateTrainingYears(TrainingWork trainingWork, TrainingRequest trainingRequest) {
        List<TrainingYear> trainingYears = trainingWork.getYears();
        boolean present = false;
        for (TrainingYear year : trainingYears) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(trainingRequest.getDate());
            if (year.getYearNumber().equals(String.valueOf(calendar.get(Calendar.YEAR)))) {
                List<TrainingMonth> months = updateTrainingMonth(year, trainingRequest);
                year.setMonths(months);
                trainingYearsRepository.save(year);
                present = true;
                log.info("Updated training year for year: {}", year.getYearNumber());
                break;
            }
        }
        if (!present) {
            TrainingYear ty = createTrainingYears(trainingRequest);
            trainingYears.add(ty);
        }
        return trainingYears;
    }

    private TrainingMonth createTrainingMonth(TrainingRequest trainingRequest) {
        TrainingMonth trainingMonth = new TrainingMonth();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(trainingRequest.getDate());
        trainingMonth.setMonthName(String.valueOf(calendar.get(Calendar.MONTH)));
        trainingMonth.setHours(trainingRequest.getDuration());
        trainingMonthRepository.save(trainingMonth);
        log.info("Created new training month for month: {} with hours: {}", trainingMonth.getMonthName(), trainingMonth.getHours());
        return trainingMonth;
    }

    private List<TrainingMonth> updateTrainingMonth(TrainingYear trainingYears, TrainingRequest trainingRequest) {
        List<TrainingMonth> trainingMonths = trainingYears.getMonths();
        boolean present = false;
        for (TrainingMonth month : trainingMonths) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(trainingRequest.getDate());
            if (month.getMonthName().equals(String.valueOf(calendar.get(Calendar.MONTH)))) {
                int hours = month.getHours() + trainingRequest.getDuration();
                month.setHours(hours);
                trainingMonthRepository.save(month);
                present = true;
                log.info("Updated training month for month: {} with new hours: {}", month.getMonthName(), month.getHours());
                break;
            }
        }
        if (!present) {
            TrainingMonth trainingMonth = createTrainingMonth(trainingRequest);
            trainingMonths.add(trainingMonth);
        }
        return trainingMonths;
    }

    private List<TrainingYear> removeTrainingMonthFromYear(List<TrainingYear> trainingYears, String year, String month, TrainingRequest trainingRequest) {
        Iterator<TrainingYear> yearIterator = trainingYears.iterator();
        while (yearIterator.hasNext()) {
            TrainingYear trainingYear = yearIterator.next();
            if (trainingYear.getYearNumber().equals(year)) {
                List<TrainingMonth> trainingMonths = trainingYear.getMonths();
                removeTrainingMonth(trainingMonths, month, trainingRequest);

                if (trainingMonths.isEmpty()) {
                    yearIterator.remove();
                    trainingYearsRepository.delete(trainingYear);
                    log.info("Deleted training year for year: {}", trainingYear.getYearNumber());
                } else {
                    trainingYear.setMonths(trainingMonths);
                    trainingYearsRepository.save(trainingYear);
                    log.info("Updated training year for year: {}", trainingYear.getYearNumber());
                }
                break;
            }
        }
        return trainingYears;
    }

    private void removeTrainingMonth(List<TrainingMonth> trainingMonths, String month, TrainingRequest trainingRequest) {
        Iterator<TrainingMonth> monthIterator = trainingMonths.iterator();
        while (monthIterator.hasNext()) {
            TrainingMonth trainingMonth = monthIterator.next();
            if (trainingMonth.getMonthName().equals(month)) {
                int result = trainingMonth.getHours() - trainingRequest.getDuration();
                if (result == 0) {
                    monthIterator.remove();
                    trainingMonthRepository.delete(trainingMonth);
                    log.info("Deleted training month for month: {} after removing hours", trainingMonth.getMonthName());
                } else {
                    trainingMonth.setHours(result);
                    trainingMonthRepository.save(trainingMonth);
                    log.info("Updated training month for month: {} with remaining hours: {}", trainingMonth.getMonthName(), trainingMonth.getHours());
                }
                break;
            }
        }

    }
}
