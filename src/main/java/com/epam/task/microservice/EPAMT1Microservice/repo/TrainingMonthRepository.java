package com.epam.task.microservice.EPAMT1Microservice.repo;

import com.epam.task.microservice.EPAMT1Microservice.model.TrainingMonth;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TrainingMonthRepository extends MongoRepository<TrainingMonth, String> {

}
