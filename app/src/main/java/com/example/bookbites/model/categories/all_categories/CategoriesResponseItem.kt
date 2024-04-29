package com.example.bookbites.model.categories.all_categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponseItem(
    val category: String,
    val id: Int
)