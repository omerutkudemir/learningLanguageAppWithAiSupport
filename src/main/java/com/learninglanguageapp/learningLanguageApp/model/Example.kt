package com.learninglanguageapp.learningLanguageApp.model

import jakarta.persistence.*
import java.util.UUID

@Entity
data class Example(
    @Id
    val id:UUID= UUID.randomUUID(),
    val question:String,
    @ElementCollection // Bu anotasyon, temel türlerin koleksiyonlarını işaretler
    @CollectionTable(
        name = "example_answers", // Koleksiyonun saklanacağı tablo adı
        joinColumns = [JoinColumn(name = "example_id")] // Örnek tablosu ile ilişki
    )
    @Column(name = "answer") // Koleksiyondaki her bir öğenin sütun adı
    val answers: List<String>,
    val trueAnswer:String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    val content: Content // Content ile ilişki

)
