package com.learninglanguageapp.learningLanguageApp.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.util.UUID
@Entity
data class UserLastTalkWithAi(
    @Id
    val id:UUID?= UUID.randomUUID(),
    @OneToMany(mappedBy = "userTalk", cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var lastConversation: List<ConversationEntry>? = mutableListOf(),
    @ManyToOne
    @JoinColumn(name = "education_id")
    var education: Education?
) {
    constructor() : this(UUID.randomUUID(), ArrayList(),null)

    constructor(lastConversation: List<String>?, education: Education) : this(
       id= UUID.randomUUID(),
        lastConversation = mutableListOf(),
        education=education
    )
}