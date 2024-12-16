package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "training_month_mongo")
public class TrainingMonth {

    @Id
    private String id;
    private String monthName;
    private Integer hours;

}
