package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.api.BookBitesApi
import com.example.bookbites.ui.uistates.ReceivedBidsUIState
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceivedBidsViewModel @Inject constructor(private val bitesApi: BookBitesApi) : ViewModel() {

    private val _receivedBids = MutableStateFlow(ReceivedBidsUIState())
    val receivedBids = _receivedBids.asStateFlow()

    init {
        getReceivedBids()
    }

    fun getReceivedBids() {
        viewModelScope.launch {
            try {
                _receivedBids.value = ReceivedBidsUIState(isLoading = true)
                val receivedBidsResponse = bitesApi.getReceivedBids()
                Log.d("RECEIVED BIDS", "${receivedBidsResponse.data?.receivedBids}")
                _receivedBids.value = when (receivedBidsResponse) {
                    is Resource.Loading -> ReceivedBidsUIState(isLoading = true)
                    is Resource.Success -> ReceivedBidsUIState(success = receivedBidsResponse.data)
                    is Resource.Error -> ReceivedBidsUIState(error("Unable to fetch received bids"))

                }

            } catch (e: Exception) {
                _receivedBids.value =
                    ReceivedBidsUIState(error = e.localizedMessage ?: "Unexpected error occured")
            }
        }
    }
}