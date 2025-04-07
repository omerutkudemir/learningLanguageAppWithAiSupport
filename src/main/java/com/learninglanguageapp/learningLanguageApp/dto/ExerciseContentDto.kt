package com.learninglanguageapp.learningLanguageApp.dto

data class ExerciseContentDto(
    val title: String,
    val education: EducationDto,
    val contentsdto: MutableList<ContentDto> = mutableListOf()
)