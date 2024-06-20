package com.example.bookbites.model.bid.create_bid

import kotlinx.serialization.Serializable

@Serializable
data class BidResponse(
    val message: String,
    val success: Boolean
)