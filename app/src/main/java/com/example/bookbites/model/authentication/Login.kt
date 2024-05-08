package com.example.bookbites.model.authentication

import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val userEmail:String,
    val userPassword:String
)