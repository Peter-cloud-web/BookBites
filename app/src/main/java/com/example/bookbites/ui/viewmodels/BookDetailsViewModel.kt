package com.example.bookbites.ui.viewmodels

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.ui.uistates.BookDetailUIState
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val bookBitesRepo: BookBitesRepo,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _booksDetail = MutableStateFlow(BookDetailUIState())
    val bookDetail = _booksDetail.asStateFlow()

    init {
        val bookId = savedStateHandle.get<Int>("bookId")
        bookId?.let { id ->
            getBooksById(id)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getBooksById(id: Int) {
        try {
            viewModelScope.launch {
                val response = bookBitesRepo.getBookById(id)
                _booksDetail.value = when (response) {
                    is Resource.Loading -> BookDetailUIState(isLoading = true)
                    is Resource.Success -> BookDetailUIState(isSuccess = response.data)
                    is Resource.Error -> BookDetailUIState(error = "Unable to get book details")
                }
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage)
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage)
        }
    }

}
