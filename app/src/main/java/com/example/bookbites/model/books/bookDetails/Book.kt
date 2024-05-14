package com.example.bookbites.model.books.bookDetails


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("author")
    val author: String,
    @SerialName("category")
    val category: String,
    @SerialName("isAvailable")
    val isAvailable: Boolean,
    @SerialName("page")
    val page: Int,
    @SerialName("summary")
    val summary: String,
    @SerialName("title")
    val title: String
)