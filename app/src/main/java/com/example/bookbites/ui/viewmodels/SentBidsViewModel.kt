package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.api.BookBitesApi
import com.example.bookbites.ui.uistates.SentBidsUIState
import com.example.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SentBidsViewModel @Inject constructor(private val bookBitesApi: BookBitesApi) : ViewModel() {

    private val _sentBids = MutableStateFlow(SentBidsUIState())
    val sentBids = _sentBids.asStateFlow()

    init {
        getSentBids()
    }

    fun getSentBids() {
        viewModelScope.launch {
            try {
                val sentBidsResponse = bookBitesApi.getSentBids()
                Log.d("SENT BIDS","${sentBidsResponse.data?.sentBids}")
                _sentBids.value = when (sentBidsResponse) {
                    is Resource.Loading -> SentBidsUIState(isLoading = true)
                    is Resource.Success -> SentBidsUIState(isSuccess = sentBidsResponse.data)
                    is Resource.Error -> SentBidsUIState(isError = "Unable to get sent bids")
                }
            } catch (e: Exception) {
                _sentBids.value =
                    SentBidsUIState(isError = e.localizedMessage ?: "Unexpected error occurred")
            }
        }
    }

}