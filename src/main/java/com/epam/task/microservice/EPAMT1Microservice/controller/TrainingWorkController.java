package com.epam.task.microservice.EPAMT1Microservice.controller;


import com.epam.task.microservice.EPAMT1Microservice.model.DTO.TrainingRequest;
import com.epam.task.microservice.EPAMT1Microservice.service.TrainingWorkService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training-work")
@RequiredArgsConstructor
public class TrainingWorkController {

    private final TrainingWorkService trainingWorkService;

    @Operation(summary = "Accept or reject training work for a trainer")
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptTrainerWork(@RequestBody TrainingRequest trainingRequest){
        trainingWorkService.acceptTrainerWork(trainingRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Add new training work",
            description = "Endpoint to add a new training work entry for a trainer",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Training work successfully added"),
                    @ApiResponse(responseCode = "400", description = "Invalid request data")
            })
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
