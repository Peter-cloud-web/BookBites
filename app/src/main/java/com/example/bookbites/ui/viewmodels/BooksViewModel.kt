package com.example.bookbites.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.ui.uistates.BookStates
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(private val bookBitesRepo: BookBitesRepo) : ViewModel() {

    private val _books = MutableStateFlow(BookStates())
    val book = _books.asStateFlow()

    init {
        getBooks()
    }

    fun getBooks() {
        try {
            viewModelScope.launch {
                val booksData = bookBitesRepo.getBooks()
                Log.d("BOOKSVIEWMODEL", "${booksData.data}")
                _books.value = when (booksData) {
                    is Resource.Loading -> BookStates(isLoading = true)
                    is Resource.Success -> BookStates(isSuccess = booksData.data)
                    is Resource.Error -> BookStates(error = "Unable to get books")
                }
            }

        } catch (e: Exception) {
            _books.value =
                BookStates(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: HttpException) {
            _books.value =
                BookStates(error = e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            _books.value =
                BookStates(error = e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    fun postBook(
        title: String,
        author: String,
        page: Int,
        category: String,
        summary: String,
        isAvailable: Boolean
    ) {
        try {
            viewModelScope.launch {
                Resource.Loading(null)
                val message =
                    bookBitesRepo.postBook(title, author, page, category, summary, isAvailable)
                Log.d("BOOKS_VIEWMODEL","${message.message}")
                Resource.Success(message)
            }
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage)
        }
    }

}

