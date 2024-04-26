package com.example.bookbites.model.bid.receivedBid

data class BiddedBook(
    val author: String,
    val category: String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)