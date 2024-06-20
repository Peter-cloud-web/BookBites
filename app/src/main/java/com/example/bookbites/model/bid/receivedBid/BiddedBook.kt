package com.example.bookbites.model.bid.receivedBid

import kotlinx.serialization.Serializable

@Serializable
data class BiddedBook(
    val author: String,
    val category: String,
    val location:String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)