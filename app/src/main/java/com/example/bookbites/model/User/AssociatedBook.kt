package com.example.bookbites.model.User

import kotlinx.serialization.Serializable

@Serializable
data class AssociatedBook(
    val author: String,
    val category: String,
    val location:String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)