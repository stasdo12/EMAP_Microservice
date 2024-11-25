package com.epam.task.microservice.EPAMT1Microservice.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    @NotNull(message = "Date is required")
    @Past(message = "Date must be in the past")
    private Date date;

    @Positive(message = "Duration must be positive")
    @NotNull(message = "Duration is required")
    private Integer duration;

    @NotBlank(message = "Action is required")
    private String action;

}
