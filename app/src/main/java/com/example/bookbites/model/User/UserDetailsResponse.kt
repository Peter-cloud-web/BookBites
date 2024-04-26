package com.example.bookbites.model.User

import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(
    val userDetailResponse:List<UserDetailsResponseItem>
)