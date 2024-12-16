package com.epam.task.microservice.EPAMT1Microservice.repo;

import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWorkMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface TrainingWorkMongoRepository extends MongoRepository<TrainingWorkMongo, UUID> {

    Optional findByUsername(String username);

    @Query("{ 'firstName': ?0, 'lastName': ?1 }")
    Optional<TrainingWorkMongo> findByFirstNameAndLastName(String firstName, String lastName);

}
