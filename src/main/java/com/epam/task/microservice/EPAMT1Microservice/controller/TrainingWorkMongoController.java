package com.epam.task.microservice.EPAMT1Microservice.controller;

import com.epam.task.microservice.EPAMT1Microservice.service.TrainingWorkMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TrainingWorkMongoController {
    @Autowired
    private TrainingWorkMongoService trainingWorkMongoService;

    @GetMapping("/addTestData")
    public String addTestData() {
        trainingWorkMongoService.addDataAndTest();
        return "Test data added successfully!";
    }



}
