package com.learninglanguageapp.learningLanguageApp.dto

data class CreateAccountRequest(
    val name:String?,
    val surName:String?,
    val email:String?,
    val password:String?

)