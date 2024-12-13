package com.epam.task.microservice.EPAMT1Microservice.repo;

import com.epam.task.microservice.EPAMT1Microservice.model.TrainingYearMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingYearMongoRepository extends MongoRepository<TrainingYearMongo, String> {


}
