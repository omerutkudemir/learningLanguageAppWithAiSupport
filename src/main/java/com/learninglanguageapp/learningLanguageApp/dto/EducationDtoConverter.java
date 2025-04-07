package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Education;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EducationDtoConverter {
    private final LanguageDtoConverter languageDtoConverter;
    private final ExerciseDtoConverter exerciseDtoConverter;
    public EducationDtoConverter(LanguageDtoConverter languageDtoConverter, ExerciseDtoConverter exerciseDtoConverter) {
        this.languageDtoConverter = languageDtoConverter;
        this.exerciseDtoConverter = exerciseDtoConverter;
    }

    public EducationDto convert(Education from) {

        return new EducationDto(
                from.getId(),
                languageDtoConverter.convert(from.getLanguage()),
                from.getEducationLevel(),
                from.getEducationHeader(),
                from.getExercise().stream().map(exerciseDtoConverter::convert).collect(Collectors.toList())
        );

    }
}
