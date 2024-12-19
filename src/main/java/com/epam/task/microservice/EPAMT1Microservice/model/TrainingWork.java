package com.epam.task.microservice.EPAMT1Microservice.model;

;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "training_work_mongo")
public class TrainingWork {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private boolean isActive;

    @DBRef
    private List<TrainingYear> years;

}
