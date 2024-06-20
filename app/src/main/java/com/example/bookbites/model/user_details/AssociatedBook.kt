package com.example.bookbites.model.user_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssociatedBook(
    @SerialName("author")
    val author: String,
    @SerialName("category")
    val category: String,
    @SerialName("location")
    val location: String,
    @SerialName("isAvailable")
    val isAvailable: Boolean,
    @SerialName("page")
    val page: Int,
    @SerialName("summary")
    val summary: String,
    @SerialName("title")
    val title: String
)