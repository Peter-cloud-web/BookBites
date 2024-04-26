package com.example.bookbites.model.books

import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    val allBooks:List<BookDetails>
)