package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.UUID
@Entity
data class Language(
    @Id
    val id:UUID?= UUID.randomUUID(),
    @Column(name = "lang_name", unique = true, nullable = false)
    val langName:String?,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language", cascade = [CascadeType.ALL])
    val educations: List<Education>? = mutableListOf() // Use a collection
) {
    constructor() : this(null, null,null)

    constructor(EducationLang: String) : this(
        langName = EducationLang
    ) {

    }
}