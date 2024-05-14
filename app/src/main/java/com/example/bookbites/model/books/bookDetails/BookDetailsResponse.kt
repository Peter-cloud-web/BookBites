package com.example.bookbites.model.books.bookDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailsResponse(
    @SerialName("book")
    val book: Book,
    @SerialName("bookId")
    val bookId: Int,
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("owner")
    val owner: String,
    @SerialName("timeOfCreation")
    val timeOfCreation: Long
)