package com.example.bookbites.model.User

data class UserDetailsResponseItem(
    val associatedBooks: List<AssociatedBook>,
    val postedAt: Long,
    val userName: String
)