package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
data class AiResponse(
    @Id
    val id: UUID=UUID.randomUUID(),
    var education: UUID?,
    @Lob
    @Column(columnDefinition = "TEXT")
    val aiText: String?,



) {
    constructor() : this(UUID.randomUUID(), null,null)

    constructor(education: UUID?, aiText: String?) : this(
        id = UUID.randomUUID(),
        education = education,
        aiText = aiText
    )
}
