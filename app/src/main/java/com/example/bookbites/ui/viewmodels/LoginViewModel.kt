package com.example.bookbites.ui.viewmodels

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.uistates.LoginStates
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val bookBitesRepo: BookBitesRepo
) :
    ViewModel() {

    private val _loginAuthState = MutableStateFlow(LoginStates())
    val loginAuthState = _loginAuthState.asStateFlow()

    fun LoginUser(email: String, password: String) {

        try {
            viewModelScope.launch {

                val token = bookBitesRepo.loginUser(email, password)

                _loginAuthState.value = when (token) {
                    is Resource.Loading -> LoginStates(isLoading = true)
                    is Resource.Success -> {
                        val userDetails = bookBitesRepo.getLoggedUser().data?.userDetailResponse
                        sessionManager.saveToken(token.data.toString())
                        LoginStates(success = AuthResponse(token.data.toString(), true))
                    }

                    is Resource.Error -> LoginStates(error("Failed to login user"))
                }

            }
        } catch (e: Exception) {
            _loginAuthState.value =
                LoginStates(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: HttpException) {
            _loginAuthState.value =
                LoginStates(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _loginAuthState.value =
                LoginStates(error = e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}
