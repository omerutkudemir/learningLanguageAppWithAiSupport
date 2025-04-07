package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.UUID
@Entity
data class Account(
    @Id
    val id:UUID?=UUID.randomUUID(),
    val name:String?,
    val surName:String?,
    val email:String?,
    val password:String?,
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
    var educations: List<Education>? = mutableListOf() // Education ile ilişki
) {
    constructor() : this(null, "", "", "", "",null)


    constructor(name: String, surName: String, mail: String, password: String) : this(
        id = UUID.randomUUID(),
        name = name,
        surName = surName,
        email = mail,
        password = password,
        educations = mutableListOf()
    )
}