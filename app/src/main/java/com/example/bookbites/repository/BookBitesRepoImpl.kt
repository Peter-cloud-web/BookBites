package com.example.bookbites.repository

import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import com.example.bookbites.api.BookBitesApi
import com.example.bookbites.model.User.UserDetailsResponse
import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.model.bid.sentBids.SentBid
import com.example.bookbites.model.books.BookResponse
import com.example.bookbites.model.books.BooksResponse
import com.example.bookbites.model.books.bookDetails.BookDetailsResponse
import com.example.bookbites.model.categories.all_categories.CategoriesResponse
import com.example.bookbites.model.categories.books_categories.CategoryBooksResponse
import com.example.util.Resource
import javax.inject.Inject

class BookBitesRepoImpl @Inject constructor(val api: BookBitesApi) : BookBitesRepo {
    override suspend fun loginUser(userEmail: String,userPassword: String): Resource<String> {
        return api.loginUser(userEmail = userEmail,userPassword = userPassword)
    }

    override suspend fun registerUser(
        userEmail: String,
        userName: String,
        userPassword: String
    ): Resource<String> {
        return api.registerUser(userEmail = userEmail,userName = userName,userPassword = userPassword)
    }

    override suspend fun getAllCategories(): Resource<CategoriesResponse> {
        return api.allCategories()
    }

    override suspend fun getBooksByCategories(category:String): Resource<CategoryBooksResponse> {
        return api.getBooksByCategories(category)
    }

    override suspend fun getSentBids(): Resource<SentBid> {
        return api.getSentBids()
    }

    override suspend fun getLoggedUser(): Resource<UserDetailsResponse> {
      return api.getLoggedUser()
    }

    override suspend fun getBooks(): Resource<BookResponse> {
        return api.getAllBooks()
    }

    override suspend fun getBookById(id: Int): Resource<BookDetailsResponse> {
        TODO("Not yet implemented")
    }

}