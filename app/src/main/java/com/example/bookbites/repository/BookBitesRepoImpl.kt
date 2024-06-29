package com.example.bookbites.repository

import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.bookbites.api.BookBitesApi
import com.example.bookbites.model.User.UserDetailsResponse
import com.example.bookbites.model.accepted_bids.AllAcceptedBidsResponse
import com.example.bookbites.model.bid.receivedBid.ReceivedBid
import com.example.bookbites.model.bid.sentBids.SentBid
import com.example.bookbites.model.books.BookResponse
import com.example.bookbites.model.books.bookDetails.BookDetailsResponse
import com.example.bookbites.model.categories.all_categories.CategoriesResponse
import com.example.bookbites.model.categories.books_categories.CategoryBooksResponse
import com.example.util.Resource
import javax.inject.Inject

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class BookBitesRepoImpl @Inject constructor(val api: BookBitesApi) : BookBitesRepo {
    override suspend fun loginUser(userEmail: String, userPassword: String): Resource<String> {
        return api.loginUser(userEmail = userEmail, userPassword = userPassword)
    }

    override suspend fun registerUser(
        userEmail: String,
        userName: String,
        firstName: String,
        lastName: String,
        userPassword: String
    ): Resource<String> {
        return api.registerUser(
            userEmail = userEmail,
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            userPassword = userPassword
        )
    }

    override suspend fun getAllCategories(): Resource<CategoriesResponse> {
        return api.allCategories()
    }

    override suspend fun createBid(
        id: Int,
        title: String,
        author: String,
        pages: Int,
        summary: String
    ): Resource<String> {
        return api.createBid(id, title, author, pages, summary)
    }

    override suspend fun getBooksByCategories(category: String): Resource<BookResponse> {
        return api.getBooksByCategories(category)
    }

    override suspend fun getBooksByLocation(location: String): Resource<BookResponse> {
        return api.getBooksByLocation(location)
    }

    override suspend fun getSentBids(): Resource<SentBid> {
        return api.getSentBids()
    }

    override suspend fun getReceivedBids(): Resource<ReceivedBid> {
        return api.getReceivedBids()
    }

    override suspend fun getLoggedUser(): Resource<UserDetailsResponse> {
        return api.getLoggedUser()
    }

    override suspend fun getBooks(): Resource<BookResponse> {
        return api.getAllBooks()
    }

    override suspend fun getBookById(id: Int): Resource<BookDetailsResponse> {
        return api.getBookById(id)
    }

    override suspend fun postBook(
        title: String,
        author: String,
        page: Int,
        category: String,
        location: String,
        summary: String,
        isAvailable: Boolean
    ): Resource<String> {
        return api.postBook(title, author, page, category, location, summary, isAvailable)
    }

    override suspend fun getUserDetails(email: String): Resource<com.example.bookbites.model.user_details.UserDetailsResponse> {
        return api.getUsersProfile(email)
    }

    override suspend fun acceptBid(id: Int): Resource<String> {
        return api.acceptBid(id)
    }

    override suspend fun getAcceptedBids(): Resource<AllAcceptedBidsResponse> {
        return api.getAcceptedBids()
    }

}