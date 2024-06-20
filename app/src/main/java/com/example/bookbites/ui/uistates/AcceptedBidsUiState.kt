package com.example.bookbites.ui.uistates

import com.example.bookbites.model.accepted_bids.AllAcceptedBidsResponseItem
import com.example.util.Resource


data class AcceptedBidsUiState(
    val isLoading:Boolean = false,
    val isSuccess: String? = null,
    val error:String = ""
)

data class AllAcceptedBidsUiState(
    val isLoading:Boolean = false,
    val isSuccess: List<AllAcceptedBidsResponseItem> = emptyList(),
    val error:String = ""
)