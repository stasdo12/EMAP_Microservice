package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "training_month_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingMonthMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String monthName;

    private Integer hours;
}
