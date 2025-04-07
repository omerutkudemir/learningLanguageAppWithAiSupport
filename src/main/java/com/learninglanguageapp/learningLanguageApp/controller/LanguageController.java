package com.learninglanguageapp.learningLanguageApp.controller;

import com.learninglanguageapp.learningLanguageApp.dto.LanguageDto;
import com.learninglanguageapp.learningLanguageApp.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/language")
public class LanguageController {
    private final LanguageService languageService;


    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

}
