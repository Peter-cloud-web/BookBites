package com.example.bookbites.model.bid.create_bid

import kotlinx.serialization.Serializable

@Serializable
data class CreateBid(
    val title:String,
    val author:String,
    val pages:Int,
    val summary:String
)
