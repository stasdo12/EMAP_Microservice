package com.epam.task.microservice.EPAMT1Microservice.repo;


import com.epam.task.microservice.EPAMT1Microservice.model.TrainingWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingWorkRepository extends JpaRepository<TrainingWork, Long> {

    Optional<TrainingWork> findByUsername(String username);

}
