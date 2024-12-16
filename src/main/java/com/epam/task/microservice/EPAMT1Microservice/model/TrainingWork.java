package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private String username;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;
    private boolean isActive;

    @DBRef
    private List<TrainingYear> years;

}
