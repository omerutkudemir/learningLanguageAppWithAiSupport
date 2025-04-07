package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Content
import com.learninglanguageapp.learningLanguageApp.model.Education
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

data class ExerciseDto(
    val header: String?,
    val isFinished:Boolean?=false,
    val contents: MutableList<ContentDto> ?= mutableListOf()
)