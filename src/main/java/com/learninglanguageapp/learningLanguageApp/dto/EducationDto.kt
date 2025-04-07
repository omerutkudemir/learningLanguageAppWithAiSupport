package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Exercise
import com.learninglanguageapp.learningLanguageApp.model.Language
import java.util.UUID

data class EducationDto(
    val id:UUID,
    val educationLang: LanguageDto?,
    val educationLevel:String?,
    val educationHeader:String?,
    val exercise: List<ExerciseDto>?

)