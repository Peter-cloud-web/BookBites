package com.example.bookbites.model.user_details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponseItem(
    @SerialName("associatedBooks")
    val associatedBooks: List<AssociatedBook>,
    @SerialName("postedAt")
    val postedAt: Long,
    @SerialName("userName")
    val userName: String,
    @SerialName("userEmail")
    val userEmail: String
)