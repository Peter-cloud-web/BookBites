package com.example.bookbites.model.accepted_bids

import kotlinx.serialization.Serializable

@Serializable
data class AcceptedBidResponse(
    val message:String,
    val success:Boolean
)