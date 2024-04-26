package com.example.bookbites.model.User

data class AssociatedBook(
    val author: String,
    val category: String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)