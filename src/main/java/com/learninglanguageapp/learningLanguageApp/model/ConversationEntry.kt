package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.*

@Entity
data class ConversationEntry(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @Lob
    @Column(columnDefinition = "TEXT")
    var sender: String?,
    @Lob
    @Column(columnDefinition = "TEXT")
    var message: String?,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "user_talk_id")
    var userTalk: UserLastTalkWithAi?
)
{
    constructor() : this(UUID.randomUUID(), null,null,null)

    constructor(sender: String?,message: String?,userTalk: UserLastTalkWithAi?) : this(
        id = UUID.randomUUID(),
        sender = sender,
        message = message,
        userTalk=userTalk
    )
}
