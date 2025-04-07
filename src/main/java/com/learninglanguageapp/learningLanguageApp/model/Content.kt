package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.*
@Entity
data class Content(
    @Id
    val id: UUID = UUID.randomUUID(),
    val contentSpace: String?,

    @OneToMany(mappedBy = "content", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val examples: List<Example>? = mutableListOf(),
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "sub_header_id")
    var exercise: Exercise?

)
{
    constructor():this(UUID.randomUUID(), null, null, null)
}
