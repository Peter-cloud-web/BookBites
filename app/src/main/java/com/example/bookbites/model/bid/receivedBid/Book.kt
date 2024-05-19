package com.example.bookbites.model.bid.receivedBid

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val author: String,
    val pages: Int,
    val summary: String,
    val title: String
)