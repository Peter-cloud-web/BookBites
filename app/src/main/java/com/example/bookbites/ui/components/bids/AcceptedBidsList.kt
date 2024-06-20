package com.example.bookbites.ui.components.bids

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookbites.model.accepted_bids.AllAcceptedBidsResponseItem
import com.example.bookbites.ui.viewmodels.AcceptedBidsViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AcceptedBidList(id: Int) {

    val acceptedBidsViewModel: AcceptedBidsViewModel = hiltViewModel()
    val acceptedBidsState =
        acceptedBidsViewModel.allAcceptedBids.collectAsStateWithLifecycle().value

    when {
        acceptedBidsState.isLoading -> {
            CircularProgressIndicator()
        }

        acceptedBidsState.isSuccess != null -> {
            acceptedBidsState.isSuccess.let { acceptedBidsList ->
                AcceptedBidItems(acceptedBidsList)
            }
        }

        acceptedBidsState.error != null -> "An unexpected error occurred"

    }


}

@Composable
fun AcceptedBidItems(
    acceptedBids: List<AllAcceptedBidsResponseItem>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(acceptedBids) { acceptedBidItem ->
                AcceptedBidItem(acceptedBidItem)
            }
        }
    }
}

