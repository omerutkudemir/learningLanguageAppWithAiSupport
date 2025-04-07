package com.learninglanguageapp.learningLanguageApp.controller;

import com.learninglanguageapp.learningLanguageApp.dto.ExerciseDto;
import com.learninglanguageapp.learningLanguageApp.service.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<ExerciseDto>> getExercise(@PathVariable("id") UUID educationId)
    {
        return ResponseEntity.ok(exerciseService.getExerciseOfEdu(educationId));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<ExerciseDto>> getCompletedExercises(UUID educationId)
    {
        return ResponseEntity.ok(exerciseService.getCompletedExerciseOfEdu(educationId));
    }
    @PostMapping
    public ResponseEntity <ExerciseDto> doExercies(@PathVariable("id") UUID exerciseId)
    {
        return ResponseEntity.ok(exerciseService.doExercise(exerciseId));
    }
}
