package com.example.bookbites.ui.uistates

import com.example.bookbites.model.books.BooksResponse

data class BookStates(
     val isLoading:Boolean = false,
     val isSuccess : BooksResponse? = null,
     val error : String = ""
 )