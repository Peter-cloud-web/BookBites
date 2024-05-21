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
import com.example.bookbites.model.bid.sentBids.BiddedBook
import com.example.bookbites.model.bid.sentBids.Book
import com.example.bookbites.ui.viewmodels.SentBidsViewModel

@Composable
fun SentBidsList() {

    val sentBidsViewModel: SentBidsViewModel = hiltViewModel()
    val sentBids = sentBidsViewModel.sentBids.collectAsState()

    sentBids.value.let { sentBidsUiState ->

        when {
            sentBidsUiState.isLoading -> {
                CircularProgressIndicator()
            }

            sentBidsUiState.isSuccess != null -> {
                sentBidsUiState.isSuccess?.sentBids.let { sentBidsList ->
                    if (sentBidsList != null) {

                        Column(
                            modifier = Modifier.fillMaxSize().padding(20.dp),
                        ) {
                            BidsList(
                                sentBidsList.map {
                                    it.biddedBook
                                }
                            )

                            BidBooksList(
                                sentBidsList.map {
                                    it.book
                                }
                            )
                        }
                    }
                }
            }

            sentBidsUiState.isError != null -> "An Unexpected error occurred"

            else -> {}
        }

    }

}


@Composable
fun BidsList(biddedBook: List<List<BiddedBook>>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(biddedBook) {
                it.map {
                    BiddedBookItem(biddedBook)
                }
            }
        }
    }
}

@Composable
fun BidBooksList(books: List<List<Book>>) {
    Column(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(books) { book ->
                BidderBook(book)
            }
        }
    }
}