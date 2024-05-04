package com.example.bookbites.model.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val message: String,
    val success: Boolean
)