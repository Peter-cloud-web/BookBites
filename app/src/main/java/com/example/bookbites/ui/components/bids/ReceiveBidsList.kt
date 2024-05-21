package com.example.bookbites.ui.components.bids

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbites.model.bid.receivedBid.BiddedBook
import com.example.bookbites.model.bid.receivedBid.Book
import com.example.bookbites.ui.viewmodels.ReceivedBidsViewModel


@Composable
fun ReceiveBidsList() {

    val receiveBidsViewModel: ReceivedBidsViewModel = hiltViewModel()
    val receivedBids = receiveBidsViewModel.receivedBids.collectAsState()

    receivedBids.value.let { receiveBidsUiState ->

        when {
            receiveBidsUiState.isLoading -> {
                CircularProgressIndicator()
            }

            receiveBidsUiState.success!= null -> {
                receiveBidsUiState.success?.receivedBids.let { receiveBidsList ->
                    if (receiveBidsList != null) {

                        Column(
                            modifier = Modifier.fillMaxSize().padding(20.dp),
                        ) {
                            ReceiveBidsList(
                                receiveBidsList.map {
                                    it.biddedBook
                                }
                            )

                            ReceiveBidBooksList(
                                receiveBidsList.map {
                                    it.book
                                }
                            )
                        }
                    }
                }
            }

            receiveBidsUiState.error != null -> "An Unexpected error occurred"

            else -> {}
        }

    }

}


@Composable
fun ReceiveBidsList(biddedBook: List<List<BiddedBook>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(biddedBook) {
                it.map {
                    ReceiveBiddedBookItem(biddedBook)
                }
            }
        }
    }
}

@Composable
fun ReceiveBidBooksList(books: List<List<Book>>) {
    Column(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(books) { book ->
                ReceiveBidderBook(book)
            }
        }
    }
}