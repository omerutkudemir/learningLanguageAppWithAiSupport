package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Education
import jakarta.persistence.CascadeType
import jakarta.persistence.OneToMany
import java.util.*
import kotlin.collections.List

data class AccountEducationDto(
    val name:String?,
    val surName:String?,
    val selectedEducation: List<EducationDto>
) {
}