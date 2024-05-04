package com.example.bookbites.model.authentication

import kotlinx.serialization.Serializable

@Serializable
data class Register(
    val userEmail:String,
    val userName:String,
    val userPassword:String
)
