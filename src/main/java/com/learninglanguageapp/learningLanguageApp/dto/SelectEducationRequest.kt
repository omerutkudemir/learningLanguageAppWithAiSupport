package com.learninglanguageapp.learningLanguageApp.dto

import com.learninglanguageapp.learningLanguageApp.model.Account
import com.learninglanguageapp.learningLanguageApp.model.Language
import jakarta.persistence.Entity
import java.util.UUID


data class SelectEducationRequest(
    val educationId:UUID?,
    val accountId: UUID?
    )