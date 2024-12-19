package com.epam.task.microservice.EPAMT1Microservice.model;



import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Data
@ToString
@Document(collection = "training_year_mongo")
public class TrainingYear {
    @Id
    private String id;
    private String yearNumber;

    private List<TrainingMonth> months;

}
