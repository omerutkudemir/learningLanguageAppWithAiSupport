package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Example;
import org.springframework.stereotype.Component;

@Component
public class ExampleDtoConverter {

    public ExampleDto convert(Example from)
    {
        return new ExampleDto(
                from.getQuestion(),
                from.getAnswers(),
                from.getTrueAnswer()
        );
    }
}
