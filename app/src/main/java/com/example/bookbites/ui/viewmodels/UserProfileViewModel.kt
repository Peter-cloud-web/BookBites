package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.ui.uistates.UserProfileIUState
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(private val bookBitesRepo: BookBitesRepo,private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    private val _userProfile = MutableStateFlow(UserProfileIUState())
    val userProfile = _userProfile.asStateFlow()

    init {
        val email = savedStateHandle.get<String>("email")
        email?.let { email ->
            getUserProfile(email)
        }
    }

    fun getUserProfile(email: String) {
        viewModelScope.launch {
            try {
                Resource.Loading(null)
                val userProfileResponse = bookBitesRepo.getUserDetails(email)
//                Log.d("USER PROFILE", "${userProfileResponse.data?.userDetailsResponseItem?.associatedBooks?.size}")
                _userProfile.value = when (userProfileResponse) {
                    is Resource.Loading -> UserProfileIUState(isLoading = true)
                    is Resource.Success -> UserProfileIUState(isSuccess = userProfileResponse.data)
                    is Resource.Error -> UserProfileIUState(isError = "Unknown error occurred")
                }
            } catch (e: Exception) {
                _userProfile.value =
                    UserProfileIUState(isError = e.localizedMessage ?: "Unexpected error occurred")
            }
        }
    }
}