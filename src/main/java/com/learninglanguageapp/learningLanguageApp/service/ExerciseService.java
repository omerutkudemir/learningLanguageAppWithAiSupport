package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.dto.ExerciseDto;
import com.learninglanguageapp.learningLanguageApp.dto.ExerciseDtoConverter;
import com.learninglanguageapp.learningLanguageApp.model.Education;
import com.learninglanguageapp.learningLanguageApp.model.Exercise;
import com.learninglanguageapp.learningLanguageApp.repository.ExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final EducationService educationService;
    private final ExerciseDtoConverter exerciseDtoConverter;

    public ExerciseService( ExerciseRepository exerciseRepository, EducationService educationService, ExerciseDtoConverter exerciseDtoConverter) {
        this.exerciseRepository = exerciseRepository;
        this.educationService = educationService;
        this.exerciseDtoConverter = exerciseDtoConverter;
    }


    public ExerciseDto doExercise(UUID exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);

        Exercise exercise = exerciseOptional.orElseThrow(() ->
                new EntityNotFoundException("Exercise not found with id: " + exerciseId)
        );
        exercise.setFinished(true);
        exercise.setFinished(true);
        return exerciseDtoConverter.convert(exerciseRepository.save(exercise));
    }

    public List<ExerciseDto> getExerciseOfEdu(UUID educationId) {
        Optional<Education> education = educationService.getEduForExercise(educationId);
        List<Exercise> exercises = education.get().getExercise();
        return exercises.stream().map(exerciseDtoConverter::convert).collect(Collectors.toList());
    }

    public List<ExerciseDto> getCompletedExerciseOfEdu(UUID educationId) {
        Optional<Education> educationOptional = educationService.getEduForExercise(educationId);

        List<Exercise> finishedExercises = educationOptional
                .map(education -> education.getExercise().stream()
                        .filter(Exercise::isFinished)
                        .collect(Collectors.toList())
                )
                .orElse(Collections.emptyList());

        return finishedExercises.stream().map(exerciseDtoConverter::convert).collect(Collectors.toList());
    }
}
