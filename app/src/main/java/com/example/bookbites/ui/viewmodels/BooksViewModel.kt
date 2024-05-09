package com.example.bookbites.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.example.bookbites.model.books.BooksResponse
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

    fun getBooks() {
        try {
            viewModelScope.launch {
                val booksData = bookBitesRepo.getBooks()
                _books.value = when (booksData) {
                    is Resource.Loading -> BookStates(isLoading = true)
                    is Resource.Success -> BookStates(isSuccess = booksData.data?.let {
                        BooksResponse(
                            it.allBooks
                        )
                    })

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

}