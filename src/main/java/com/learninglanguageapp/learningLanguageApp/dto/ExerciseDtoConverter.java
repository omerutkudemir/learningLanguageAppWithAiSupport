package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Exercise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ExerciseDtoConverter {

    private final ContentDtoConverter contentDtoConverter;

    public ExerciseDtoConverter(ContentDtoConverter contentDtoConverter) {
        this.contentDtoConverter = contentDtoConverter;
    }

    public ExerciseDto convert(Exercise from) {
        List<ContentDto> contentDtoList = from.getContents().stream()
                .map(contentDtoConverter::convert)
                .collect(Collectors.toList());

        return new ExerciseDto(
                from.getHeader(),
                from.isFinished(),
                contentDtoList
        );
    }
}
