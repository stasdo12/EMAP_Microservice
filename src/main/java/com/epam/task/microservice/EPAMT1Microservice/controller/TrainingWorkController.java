package com.epam.task.microservice.EPAMT1Microservice.controller;


import com.epam.task.microservice.EPAMT1Microservice.model.DTO.TrainingRequest;
import com.epam.task.microservice.EPAMT1Microservice.service.TrainingWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/training-work")
@RequiredArgsConstructor
public class TrainingWorkController {

    @Autowired
    private final TrainingWorkService trainingWorkService;

    @Operation(summary = "Accept or reject training work for a trainer")
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptTrainerWork(@RequestBody TrainingRequest trainingRequest){
        trainingWorkService.acceptTrainerWork(trainingRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Add new training work")
    @PostMapping("/add")
    public ResponseEntity<Void> addTrainingWork(@RequestBody TrainingRequest trainingRequest){
        trainingWorkService.addTrainingWork(trainingRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a trainer's training work")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTrainingWork(@RequestBody TrainingRequest trainingRequest){
        trainingWorkService.deleteTrainingWork(trainingRequest);
        return ResponseEntity.ok().build();
    }

}
