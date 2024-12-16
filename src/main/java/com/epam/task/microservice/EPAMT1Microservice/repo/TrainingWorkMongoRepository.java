package com.epam.task.microservice.EPAMT1Microservice.repo;

import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWorkMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface TrainingWorkMongoRepository extends MongoRepository<TrainingWorkMongo, UUID> {

    TrainingWorkMongo findByUsername(String username);
}
