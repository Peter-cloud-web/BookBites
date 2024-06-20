package com.example.bookbites.ui.sceens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import com.example.bookbites.ui.components.bids.AcceptedBidList

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AcceptedBidsScreen(id:Int) {
    AcceptedBidList(id)
}

