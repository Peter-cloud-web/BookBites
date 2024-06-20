package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.model.bid.create_bid.BidResponse
import com.example.bookbites.repository.BookBitesRepo
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateBidViewModel @Inject constructor(
    private val repo: BookBitesRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val _bidResponse = MutableStateFlow<Resource<BidResponse>>(Resource.Loading(null))
    val bidResponse = _bidResponse.asStateFlow()

    val bookId = savedStateHandle.get<Int>("bookId")

    fun CreateBid(title: String, author: String, page: Int, summary: String) {

        viewModelScope.launch {

            _bidResponse.value = Resource.Loading(null)

            Log.d("BOOKID", "${bookId}")

            val message = bookId?.let { id -> repo.createBid(id, title, author, page, summary) }

            Log.d("CREATEBID", "${message}")

            if (message is Resource.Success) {
                _bidResponse.value =
                    Resource.Success(BidResponse(message.message.toString(), true))
            } else {
                _bidResponse.value = Resource.Error(null, "")
            }

        }
    }
}
