package com.example.bookbites.model.bid.sentBids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BiddedBook(
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