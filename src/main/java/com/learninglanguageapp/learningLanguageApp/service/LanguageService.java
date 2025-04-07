package com.learninglanguageapp.learningLanguageApp.service;

import com.learninglanguageapp.learningLanguageApp.dto.LanguageDto;
import com.learninglanguageapp.learningLanguageApp.dto.LanguageDtoConverter;
import com.learninglanguageapp.learningLanguageApp.model.Language;
import com.learninglanguageapp.learningLanguageApp.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LanguageService {
    private final LanguageDtoConverter languageDtoConverter;
    private final LanguageRepository languageRepository;

    public LanguageService(LanguageDtoConverter languageDtoConverter, LanguageRepository languageRepository) {
        this.languageDtoConverter = languageDtoConverter;
        this.languageRepository = languageRepository;
    }

    public List<LanguageDto> getAvailableLangs() {
       List<Language> languageList= languageRepository.findAll();

       return languageList.stream().map(languageDtoConverter::convert).collect(Collectors.toList());
    }
    public Language getlLang(String name)
    {
       Language language= languageRepository.findByLangName(name);
       return language;
    }



}
