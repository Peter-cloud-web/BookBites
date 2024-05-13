package com.example.bookbites.ui.uistates

import com.example.bookbites.model.books.BookResponse
import com.example.bookbites.model.books.BooksResponse

data class BookStates(
    val isLoading:Boolean = false,
    val isSuccess : BookResponse? = null,
    val error : String = ""
 )