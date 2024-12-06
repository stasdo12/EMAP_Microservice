package com.epam.task.microservice.EPAMT1Microservice.repo;

import com.epam.task.microservice.EPAMT1Microservice.model.TrainingYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingYearRepository extends JpaRepository<TrainingYear, Long> {

}
