package com.example.bookbites.model.bid.receivedBid

import kotlinx.serialization.Serializable

@Serializable
data class ReceivedBidItem(
    val biddedBook: List<BiddedBook>,
    val biddedBookId: Int,
    val bidder: String,
    val book: List<Book>
)