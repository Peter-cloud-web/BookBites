package com.example.bookbites.model.categories

data class CategoryBooksItem(
    val bookOwner: String,
    val books: List<Book>,
    val category: String,
    val postedAt: Long
)