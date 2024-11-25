package com.epam.task.microservice.EPAMT1Microservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Entity
@Data
@ToString
public class TrainingYear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String yearNumber;

    @OneToMany
    private List<TrainingMonth> months;

}
