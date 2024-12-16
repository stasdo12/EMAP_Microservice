package com.epam.task.microservice.EPAMT1Microservice.service;


import com.epam.task.microservice.EPAMT1Microservice.model.TrainingMonthMongo;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWorkMongo;
import com.epam.task.microservice.EPAMT1Microservice.model.TrainingYearMongo;
import com.epam.task.microservice.EPAMT1Microservice.repo.TrainingWorkMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@Service
public class TrainingWorkMongoService {

    @Autowired
    private final TrainingWorkMongoRepository trainingWorkMongoRepository;

    public TrainingWorkMongoService(TrainingWorkMongoRepository trainingWorkMongoRepository) {
        this.trainingWorkMongoRepository = trainingWorkMongoRepository;
    }


    public void addDataAndTest() {
        TrainingWorkMongo trainingWork1 = new TrainingWorkMongo();
        trainingWork1.setUsername("john_doe");
        trainingWork1.setFirstName("John");
        trainingWork1.setLastName("Doe");
        trainingWork1.setActive(true);

        TrainingYearMongo year2024 = new TrainingYearMongo(null, "2024", List.of(
                new TrainingMonthMongo(null, Month.JANUARY.name(), 10),
                new TrainingMonthMongo(null, Month.FEBRUARY.name(), 20)
        ));

        TrainingYearMongo year2023 = new TrainingYearMongo(null,"2023", List.of(
                new TrainingMonthMongo(null, Month.DECEMBER.name(), 15)
        ));

        trainingWork1.getYears().add(year2024);
        trainingWork1.getYears().add(year2023);

        trainingWorkMongoRepository.insert(trainingWork1);

    }


    public TrainingWorkMongo findTrainingWorkByUsername(String username) {
        return trainingWorkMongoRepository.findByUsername(username);
    }
}
