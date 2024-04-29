package com.example.bookbites.model.bid.sentBids


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SentBid(
    val sentBids: List<SentBidItem>
)