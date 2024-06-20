package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import com.example.bookbites.ui.components.bids.ReceiveBidsList


@Composable
fun ReceivedBidsScreen(onAcceptedBidClick:(id:Int) -> Unit) {
    ReceiveBidsList(onAcceptedBidClick = onAcceptedBidClick)
}