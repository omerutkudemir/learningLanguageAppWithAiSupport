package com.learninglanguageapp.learningLanguageApp.dto;

import com.learninglanguageapp.learningLanguageApp.model.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageDtoConverter {

    public LanguageDto convert(Language from) {
        return new LanguageDto(
               from.getLangName()
        );
    }
}
