package com.example.bookbites.model.bid.sentBids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SentBidItem(
    @SerialName("biddedBook")
    val biddedBook: List<BiddedBook>,
    @SerialName("biddedBookId")
    val biddedBookId: Int,
    @SerialName("biddedBookOwner")
    val biddedBookOwner: String,
    @SerialName("book")
    val book: List<Book>
)