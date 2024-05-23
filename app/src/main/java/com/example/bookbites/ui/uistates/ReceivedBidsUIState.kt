package com.example.bookbites.ui.uistates

import com.example.bookbites.model.bid.receivedBid.ReceivedBid

data class ReceivedBidsUIState(
    val isLoading:Boolean = false,
    val success:ReceivedBid? = null,
    val error:String = ""

)