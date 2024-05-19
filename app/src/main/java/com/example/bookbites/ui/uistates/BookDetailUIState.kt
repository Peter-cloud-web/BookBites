package com.example.bookbites.ui.uistates

import com.example.bookbites.model.books.bookDetails.BookDetailsResponse

data class BookDetailUIState(
    val isLoading: Boolean = false,
    val isSuccess: BookDetailsResponse? = null,
    val error: String = ""
)