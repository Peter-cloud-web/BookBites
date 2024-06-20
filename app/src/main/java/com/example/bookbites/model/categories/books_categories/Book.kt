package com.example.bookbites.model.categories.books_categories

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val author: String,
    val category: String,
    val location:String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)