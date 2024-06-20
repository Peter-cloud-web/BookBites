package com.example.bookbites.ui.viewmodels

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.ui.uistates.AcceptedBidsUiState
import com.example.bookbites.ui.uistates.AllAcceptedBidsUiState
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class AcceptedBidsViewModel @Inject constructor(
    private val bookBitesRepo: BookBitesRepo,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    val _acceptedBids = MutableStateFlow(AcceptedBidsUiState())
    val acceptedBids = _acceptedBids.asStateFlow()

    val _allAcceptedBids = MutableStateFlow(AllAcceptedBidsUiState())
    val allAcceptedBids = _allAcceptedBids.asStateFlow()


    init {
        val id = savedStateHandle.get<Int>("bidId")

        if (id != null) {
            acceptBid(id)
        }else{
            null
        }

        getAcceptedBids()
    }

    fun acceptBid(id: Int) {
        try {
            viewModelScope.launch {
                Resource.Loading(null)
                val response = bookBitesRepo.acceptBid(id)
                Log.d("ACCEPTED BID", "${response.message}" + "${id}")
                _acceptedBids.value = when (response) {
                    is Resource.Loading -> AcceptedBidsUiState(isLoading = true)
                    is Resource.Success -> AcceptedBidsUiState(isSuccess = response.message)
                    is Resource.Error -> AcceptedBidsUiState(error = "Unable to get book details")
                }
            }
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage)
        }

    }

    fun getAcceptedBids() {
        try {
            viewModelScope.launch {
                Resource.Loading(null)
                val allAcceptedBidsResponse = bookBitesRepo.getAcceptedBids()

                _allAcceptedBids.value = when (allAcceptedBidsResponse) {
                    is Resource.Loading -> AllAcceptedBidsUiState(isLoading = true)
                    is Resource.Success -> AllAcceptedBidsUiState(isSuccess = allAcceptedBidsResponse.data?.allAcceptedBids!!)
                    is Resource.Error -> AllAcceptedBidsUiState(error = "Unable to load data")
                }
            }
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage)
        }

    }
}