package com.example.bookbites.model.books

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetails(
    val book: Book,
    val bookId: Int,
    val categoryId: Int,
    val owner: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    val timeOfCreation: Long
)