package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Content;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ContentDtoConverter {

private final ExampleDtoConverter exampleDtoConverter;

    public ContentDtoConverter(ExampleDtoConverter exampleDtoConverter) {
        this.exampleDtoConverter = exampleDtoConverter;
    }


    public ContentDto convert(Content from)
    {
        return new ContentDto(
                from.getContentSpace(),
                from.getExamples().stream().map(exampleDtoConverter::convert).collect(Collectors.toList())

        );
    }
}
