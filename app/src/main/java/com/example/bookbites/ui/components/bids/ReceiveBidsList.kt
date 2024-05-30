package com.example.bookbites.ui.components.bids

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookbites.model.bid.receivedBid.BiddedBook
import com.example.bookbites.model.bid.receivedBid.Book
import com.example.bookbites.ui.viewmodels.ReceivedBidsViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun ReceiveBidsList() {
    val receiveBidsViewModel: ReceivedBidsViewModel = hiltViewModel()
    val receivedBidsState = receiveBidsViewModel.receivedBids.collectAsStateWithLifecycle().value
    when {
        receivedBidsState.isLoading -> {
            CircularProgressIndicator()
        }
        receivedBidsState.success != null -> {
            receivedBidsState.success?.receivedBids?.let { receiveBidsList ->
                if (receiveBidsList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(receiveBidsList) { receivedBidItem ->
                            Column {
                                receivedBidItem.biddedBook.forEach { biddedBook ->
                                    ReceiveBiddedBookItem(biddedBook)
                                }

                                receivedBidItem.book.forEach { book ->
                                    ReceiveBidderBook(book)
                                }

                            }
                        }
                    }
                }
            }
        }
        receivedBidsState.error != null -> {
            Text(text = receivedBidsState.error, color = Color.Red)
        }
        else -> {}
    }
}
