package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Education
import java.util.*

data class UpdateEducationRequest(
    val accountId: UUID,
    val educationLang:String,
    val lastExerciesLevel:String
)