package com.example.bookbites.model.bid.receivedBid

data class ReceivedBidItem(
    val biddedBook: List<BiddedBook>,
    val biddedBookId: Int,
    val bidder: String,
    val book: List<Book>
)