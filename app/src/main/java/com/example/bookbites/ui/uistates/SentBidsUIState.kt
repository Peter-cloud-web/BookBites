package com.example.bookbites.ui.uistates

import com.example.bookbites.model.bid.sentBids.SentBid

data class SentBidsUIState(
    val isLoading:Boolean = false,
    val isSuccess:SentBid? = null,
    val isError:String = ""
)