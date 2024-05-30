package com.example.bookbites.model.books


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponseItem(
    @SerialName("book")
    val book: BookX,
    @SerialName("bookId")
    val bookId: Int,
    @SerialName("categoryId")
    val categoryId: Int,
    @SerialName("owner")
    val owner: String,
    @SerialName("email")
    val email:String,
    @SerialName("timeOfCreation")
    val timeOfCreation: Long
)