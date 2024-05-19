package com.example.bookbites.model.books

import kotlinx.serialization.Serializable

@Serializable
data class PostBookResponse(
val message: String,
val success: Boolean
)
