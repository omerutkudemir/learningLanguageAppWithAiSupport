package com.learninglanguageapp.learningLanguageApp.dto

import java.util.*

data class AccountDto(
    val id: UUID,
    val name:String,
    val surName:String,
    val email:String,
    val password: String,
)