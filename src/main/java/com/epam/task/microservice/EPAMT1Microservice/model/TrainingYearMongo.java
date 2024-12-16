package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document(collection = "training_year_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingYearMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String yearNumber;

    @Field("months")
    private List<TrainingMonthMongo> months;



}
