package com.epam.task.microservice.EPAMT1Microservice.repo;


import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWork;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingWorkRepository extends MongoRepository<TrainingWork, String> {

    Optional<TrainingWork> findByUsername(String username);


}
