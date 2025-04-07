package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Language

data class EducationSubHeaderDto(
    val educationLang: Language,
    val educationLevel:String,
    val educationHeader:String,
    val subHeaders: List<ExerciseDto> = mutableListOf(),

    )