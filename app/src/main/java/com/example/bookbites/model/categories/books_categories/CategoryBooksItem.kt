package com.example.bookbites.model.categories.books_categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoryBooksItem(
    val bookOwner: String,
    val books: List<Book>,
    val category: String,
    val postedAt: Long
)