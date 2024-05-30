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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookbites.model.bid.sentBids.BiddedBook
import com.example.bookbites.model.bid.sentBids.Book
import com.example.bookbites.ui.viewmodels.SentBidsViewModel

@Composable
fun SentBidsList() {

    val sentBidsViewModel: SentBidsViewModel = hiltViewModel()
    val sentBids = sentBidsViewModel.sentBids.collectAsStateWithLifecycle()

    sentBids.value.let { sentBidsUiState ->

        when {
            sentBidsUiState.isLoading -> {
                CircularProgressIndicator()
            }

            sentBidsUiState.isSuccess != null -> {
                sentBidsUiState.isSuccess?.sentBids.let { sentBidsList ->
                    if (sentBidsList != null) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(sentBidsList) { sentBidItem ->
                                Column {
                                    sentBidItem.biddedBook.forEach { biddedBook ->
                                        BiddedBookItem(biddedBook)
                                    }

                                    sentBidItem.book.forEach { book ->
                                        BidderBook(book)
                                    }

                                }
                            }
                        }
                    }
                }
            }

            sentBidsUiState.isError != null -> "An Unexpected error occurred"

            else -> {}
        }

    }

}
