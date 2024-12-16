package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "training_work_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingWorkMongo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;

    @Field("years")
    private List<TrainingYearMongo> years;


    public List<TrainingYearMongo> getYears() {
        if (years == null) {
            years = new ArrayList<>();
        }
        return years;
    }

}

