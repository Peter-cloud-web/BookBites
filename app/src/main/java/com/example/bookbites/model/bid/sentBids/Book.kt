package com.example.bookbites.model.bid.sentBids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("author")
    val author: String,
    @SerialName("pages")
    val pages: Int,
    @SerialName("summary")
    val summary: String,
    @SerialName("title")
    val title: String
)