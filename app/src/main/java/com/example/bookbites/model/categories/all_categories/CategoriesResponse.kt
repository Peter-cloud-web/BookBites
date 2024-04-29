package com.example.bookbites.model.categories.all_categories

import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    val categories:List<CategoriesResponse>
)