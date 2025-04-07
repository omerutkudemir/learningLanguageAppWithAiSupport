package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Example
import jakarta.persistence.CascadeType
import jakarta.persistence.FetchType
import jakarta.persistence.OneToMany

data class ContentDto(
    val contentSpace: String,
    val examples: List<ExampleDto> = mutableListOf(),

    )