package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.UUID
@Entity
data class Exercise(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:UUID?= UUID.randomUUID(),
    val header: String?,
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinColumn(name = "education_id")
    var education: Education?,

    var isFinished:Boolean?=false,
    @OneToMany(mappedBy = "exercise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var contents: MutableList<Content>? = mutableListOf()
)
{
    constructor() : this(null, null, null, false, null)

}
