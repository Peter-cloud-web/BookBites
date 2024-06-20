package com.example.bookbites.model.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponseItem(
    val associatedBooks: List<AssociatedBook>,
    val postedAt: Long,
    val userName: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String
)