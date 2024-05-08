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
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val bookBitesRepo: BookBitesRepo
) :
    ViewModel() {

    private val _loginAuthState = MutableStateFlow<Resource<AuthResponse>>(Resource.Loading(null))
    val loginAuthState: StateFlow<Resource<AuthResponse>> = _loginAuthState

    fun LoginUser(email: String, password: String) {

        viewModelScope.launch {

            _loginAuthState.value = Resource.Loading(null)

            val token = bookBitesRepo.loginUser(email, password)

            if (token is Resource.Success) {

                Log.d("LOGINVIEWMODEL","${token.data.toString()}")

                sessionManager.saveToken(token.data.toString())

                _loginAuthState.value = Resource.Success(AuthResponse(token.data.toString(), true))

                val userDetails = bookBitesRepo.getLoggedUser().data?.userDetailResponse

                Log.d("LOGINVIEWMODEL","${userDetails}")


            } else {
                _loginAuthState.value = Resource.Error(null, "Failed to login user")
            }
        }
    }
}