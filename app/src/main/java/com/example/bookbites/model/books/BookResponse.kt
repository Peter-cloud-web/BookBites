package com.example.bookbites.model.books


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class BookResponse(
    val books: List<BookResponseItem>? = emptyList()
)