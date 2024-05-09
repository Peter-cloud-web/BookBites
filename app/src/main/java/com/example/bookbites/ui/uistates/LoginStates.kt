package com.example.bookbites.ui.uistates

import com.example.bookbites.model.authentication.AuthResponse

data class LoginStates(
    val isLoading: Boolean = false,
    val success: AuthResponse? = null,
    val error: String = ""
)
