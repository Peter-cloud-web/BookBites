package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.store.SessionManager
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistationViewModel @Inject constructor(
    private val bookBitesRepo: BookBitesRepo,
    private val dataStore: SessionManager
) : ViewModel() {

    private val _authState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading(null))
    val authState: StateFlow<Resource<AuthResponse>> = _authState

    fun registerUser(email: String, username: String, firstName:String,lastName:String,password: String) {

        viewModelScope.launch {

            _authState.value = Resource.Loading(null)

            val token = bookBitesRepo.registerUser(userEmail = email, userName = username, firstName = firstName,lastName = lastName, userPassword = password)

            if (token is Resource.Success) {

                dataStore.saveToken(token.data.toString())

                val savedToken = dataStore.getToken.toString()

                _authState.value = Resource.Success(AuthResponse(savedToken, true))
            }else{
                _authState.value = Resource.Error(null,"Failed to register user")
            }

        }
    }
}