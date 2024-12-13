package com.epam.task.microservice.EPAMT1Microservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "training_year_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingYearMongo {

    @Id
    private String id;
    private String yearNumber;

    @Field("months")
    private List<TrainingMonthMongo> months;


}
