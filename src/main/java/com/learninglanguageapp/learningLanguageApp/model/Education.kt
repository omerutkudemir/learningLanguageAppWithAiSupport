package com.learninglanguageapp.learningLanguageApp.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.UUID

@Entity
data class Education(
    @Id
    val id: UUID? = UUID.randomUUID(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "lang_id")
    var language: Language?,

    var educationLevel: String,
    val educationHeader: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "education", cascade = [CascadeType.ALL], orphanRemoval = true)
    var exercise: List<Exercise>? = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "account_id")
    var account: Account?, // Account ile ilişki
    @OneToMany(mappedBy = "education", cascade = [CascadeType.MERGE])
    var userLastTalkWithAi: List<UserLastTalkWithAi>?
) {
    constructor() : this(null, null, "", "", null, null,null)

    constructor(educationHeader: String, educationLang: Language, educationLevel: String, account: Account,userLastTalkWithAi: List<UserLastTalkWithAi>) : this(
        id = UUID.randomUUID(),
        educationHeader = educationHeader,
        language = educationLang,
        educationLevel = educationLevel,
        exercise = mutableListOf(),
        account = null,
        userLastTalkWithAi = userLastTalkWithAi
    )
}