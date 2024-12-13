package com.epam.task.microservice.EPAMT1Microservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "training_month_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingMonthMongo {

    @Id
    private String id;

    private String monthName;

    private Integer hours;
}
