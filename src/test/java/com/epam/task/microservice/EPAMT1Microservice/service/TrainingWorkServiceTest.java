package com.epam.task.microservice.EPAMT1Microservice.service;

import com.epam.task.microservice.EPAMT1Microservice.model.DTO.TrainingRequest;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingMonth;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWork;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingYear;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingMonthRepository;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingWorkRepository;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingYearRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.MDC;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import jakarta.ws.rs.NotFoundException;
import static org.mockito.Mockito.*;

import java.util.*;

class TrainingWorkServiceTest {
    @InjectMocks
    private TrainingWorkService trainingWorkService;
    @Mock
    private TrainingWorkRepository trainingWorkRepository;
    @Mock
    private TrainingYearRepository trainingYearRepository;
    @Mock
    private TrainingMonthRepository trainingMonthRepository;
    private TrainingRequest trainingRequest;
    private TrainingWork trainingWork;
    private TrainingYear trainingYear;
    private TrainingMonth trainingMonth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MDC.put("Transaction-ID", "12345");

        trainingRequest = new TrainingRequest();
        trainingRequest.setAction("add");
        trainingRequest.setUsername("testUser");
        trainingRequest.setFirstName("John");
        trainingRequest.setLastName("Doe");
        trainingRequest.setIsActive(true);
        trainingRequest.setDate(new Date());
        trainingRequest.setDuration(5);

        trainingMonth = new TrainingMonth();
        trainingMonth.setMonthName("10");
        trainingMonth.setHours(5);

        trainingYear = new TrainingYear();
        trainingYear.setYearNumber("2024");
        trainingYear.setMonths(new ArrayList<>(List.of(trainingMonth)));

        trainingWork = new TrainingWork();
        trainingWork.setUsername("testUser");
        trainingWork.setFirstName("John");
        trainingWork.setLastName("Doe");
        trainingWork.setActive(true);
        trainingWork.setYears(new ArrayList<>(List.of(trainingYear)));
        when(trainingWorkRepository.findByUsername("testUser"))
                .thenReturn(Optional.of(trainingWork));
    }

    @Test
    void acceptTrainerWork_addNewTrainingWork() {
        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.empty());

        trainingWorkService.acceptTrainerWork(trainingRequest);

        verify(trainingWorkRepository, times(1)).save(any(TrainingWork.class));
    }

    @Test
    void acceptTrainerWork_deleteExistingTrainingWork() {
        trainingRequest.setAction("delete");

        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.of(trainingWork));

        trainingWorkService.acceptTrainerWork(trainingRequest);

        verify(trainingWorkRepository, times(1)).findByUsername("testUser");
        verify(trainingWorkRepository, times(1)).delete(any(TrainingWork.class));
        verify(trainingWorkRepository, never()).save(any());
    }

    @Test
    void addTrainingWork_createsNewTrainingWork() {
        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.empty());

        trainingWorkService.addTrainingWork(trainingRequest);

        verify(trainingWorkRepository, times(1)).save(any(TrainingWork.class));
    }

    @Test
    void addTrainingWork_updatesExistingTrainingWork() {
        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.of(trainingWork));

        trainingWorkService.addTrainingWork(trainingRequest);

        verify(trainingWorkRepository, times(1)).save(trainingWork);
    }

    @Test
    void deleteTrainingWork_removesTrainingWork() {
        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.of(trainingWork));

        trainingWorkService.deleteTrainingWork(trainingRequest);

        verify(trainingWorkRepository, times(1)).delete(trainingWork);
    }
    @Test
    void deleteTrainingWork_throwsNotFoundException() {

        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                trainingWorkService.deleteTrainingWork(trainingRequest));

        assertEquals("Training work not found for username: " + trainingRequest.getUsername(),
                exception.getMessage());
    }

    @Test
    void updateTrainingWork_addsNewYear() {
        when(trainingWorkRepository.findByUsername(trainingRequest.getUsername())).thenReturn(Optional.of(trainingWork));

        trainingRequest.setDate(new GregorianCalendar(2025, Calendar.JANUARY, 1).getTime());

        trainingWorkService.addTrainingWork(trainingRequest);

        verify(trainingYearRepository, times(1)).save(any(TrainingYear.class));
    }

    @Test
    void removeTrainingMonth_deletesMonthIfHoursZero() {
        trainingRequest.setDuration(5);

        trainingWorkService.deleteTrainingWork(trainingRequest);

        verify(trainingMonthRepository, times(1)).delete(trainingMonth);
    }

    @Test
    void removeTrainingMonth_updatesMonthIfHoursRemain() {
        trainingMonth.setHours(10);
        trainingRequest.setDuration(5);

        trainingWorkService.deleteTrainingWork(trainingRequest);

        assertEquals(5, trainingMonth.getHours());
        verify(trainingMonthRepository, times(1)).save(trainingMonth);
    }
}