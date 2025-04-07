package com.learninglanguageapp.learningLanguageApp.dto

data class ExerciseAnalysisRequest(
    val educationDto: EducationDto,
    val exampleDto: List<ExampleDto>,
)
