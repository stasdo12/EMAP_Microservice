package com.epam.task.microservice.EPAMT1Microservice.controller;

import com.epam.task.microservice.EPAMT1Microservice.config.TestSecurityConfig;
import com.epam.task.microservice.EPAMT1Microservice.model.DTO.TrainingRequest;
import com.epam.task.microservice.EPAMT1Microservice.service.TrainingWorkService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainingWorkController.class)
@Import(TestSecurityConfig.class)
class TrainingWorkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingWorkService trainingWorkService;

    private ObjectMapper objectMapper;
    private TrainingRequest trainingRequest;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        trainingRequest = new TrainingRequest();

        trainingRequest.setUsername("testUser");
        trainingRequest.setFirstName("John");
        trainingRequest.setLastName("Doe");
        trainingRequest.setIsActive(true);
        trainingRequest.setDate(new Date(2024 - 1900, 11, 3));
        trainingRequest.setDuration(10);
        trainingRequest.setAction("someAction");
    }

    @Test
    void acceptTrainerWork_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/training-work/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Transaction-ID", "12345")
                        .content(objectMapper.writeValueAsString(trainingRequest)))
                .andExpect(status().isOk());

        verify(trainingWorkService, times(1)).acceptTrainerWork(trainingRequest);
    }

    @Test
    void addTrainingWork_shouldReturnOk() throws Exception {
        mockMvc.perform(post("/training-work/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingRequest)))
                .andExpect(status().isOk());

        verify(trainingWorkService, times(1)).addTrainingWork(trainingRequest);
    }

    @Test
    void deleteTrainingWork_shouldReturnOk() throws Exception {
        mockMvc.perform(delete("/training-work/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingRequest)))
                .andExpect(status().isOk());

        verify(trainingWorkService, times(1)).deleteTrainingWork(trainingRequest);
    }

    @Test
    void acceptTrainerWork_missingTransactionId_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/training-work/accept")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(trainingRequest)))
                .andExpect(status().isBadRequest());
    }
}