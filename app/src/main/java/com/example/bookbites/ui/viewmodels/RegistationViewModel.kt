package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.store.DataStore
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistationViewModel @Inject constructor(
    private val bookBitesRepo: BookBitesRepo,
    private val dataStore: DataStore
) : ViewModel() {

    private val _authState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading(null))
    val authState: StateFlow<Resource<AuthResponse>> = _authState

    fun registerUser(email: String, username: String, password: String) {
        viewModelScope.launch {
            _authState.value = Resource.Loading(null)
            val result = bookBitesRepo.registerUser(userEmail = email, userName = username, userPassword = password)
            Log.d("REGISTRATION VIEWMODEL","Data : ${email},${username},${password}")
            if (result is Resource.Success) {
                result.data
            }
        }
    }

    private suspend fun saveToken(token: String) {
        dataStore.saveToken(token)
    }
}