package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingMonth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String monthName;
    private Integer hours;

}
