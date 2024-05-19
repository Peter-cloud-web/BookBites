package com.example.bookbites.model.bid.receivedBid

import kotlinx.serialization.Serializable

@Serializable
data class ReceivedBid(
    val receivedBids: List<ReceivedBidItem>
)