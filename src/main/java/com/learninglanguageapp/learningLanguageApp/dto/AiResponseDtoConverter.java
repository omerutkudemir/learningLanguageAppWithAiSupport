package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.AiResponse;
import org.springframework.stereotype.Component;

@Component
public class AiResponseDtoConverter {
    public AiResponseDto convert(AiResponse from)
    {
        return new AiResponseDto(
                from.getAiText()
        );
    }
}
