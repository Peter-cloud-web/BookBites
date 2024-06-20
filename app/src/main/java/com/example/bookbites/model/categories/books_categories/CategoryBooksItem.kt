package com.example.bookbites.model.categories.books_categories

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryBooksItem(
    val bookOwner: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    val books: List<Book>,
    val category: String,
    val postedAt: Long
)