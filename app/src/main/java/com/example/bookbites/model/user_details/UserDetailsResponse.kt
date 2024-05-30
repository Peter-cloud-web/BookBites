package com.example.bookbites.model.user_details


import kotlinx.serialization.Serializable

@Serializable
data class UserDetailsResponse(val userDetailsResponseItem: List<UserDetailsResponseItem>)