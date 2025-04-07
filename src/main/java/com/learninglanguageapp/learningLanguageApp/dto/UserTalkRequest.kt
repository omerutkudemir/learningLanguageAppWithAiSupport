package com.learninglanguageapp.learningLanguageApp.dto

import java.util.UUID

data class UserTalkRequest(
    val educationId:UUID,
    val text:String
)
