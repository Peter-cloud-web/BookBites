package com.example.bookbites.model.categories

data class Book(
    val author: String,
    val category: String,
    val isAvailable: Boolean,
    val page: Int,
    val summary: String,
    val title: String
)