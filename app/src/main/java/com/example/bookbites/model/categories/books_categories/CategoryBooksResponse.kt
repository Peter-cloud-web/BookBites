package com.example.bookbites.model.categories.books_categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoryBooksResponse(
    val categoryBooks:List<CategoryBooksItem>
)