package com.epam.task.microservice.EPAMT1Microservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "training_work_mongo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingWorkMongo {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;

    @Field("years")
    private List<TrainingYearMongo> years;
}
