package com.example.bookbites.model.books

import kotlinx.serialization.Serializable

@Serializable
data class BookDetails(
    val book: Book,
    val bookId: Int,
    val categoryId: Int,
    val owner: String,
    val timeOfCreation: Long
)