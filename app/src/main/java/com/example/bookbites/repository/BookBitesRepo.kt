package com.example.bookbites.repository


import com.example.bookbites.model.User.UserDetailsResponse
import com.example.bookbites.model.accepted_bids.AllAcceptedBidsResponse
import com.example.bookbites.model.bid.receivedBid.ReceivedBid
import com.example.bookbites.model.bid.sentBids.SentBid
import com.example.bookbites.model.books.BookResponse
import com.example.bookbites.model.books.bookDetails.BookDetailsResponse
import com.example.bookbites.model.categories.all_categories.CategoriesResponse
import com.example.bookbites.model.categories.books_categories.CategoryBooksResponse
import com.example.util.Resource

interface BookBitesRepo {

    suspend fun loginUser(userEmail: String, userPassword: String): Resource<String>
    suspend fun registerUser(
        userEmail: String,
        userName: String,
        firstName: String,
        lastName: String,
        userPassword: String
    ): Resource<String>

    suspend fun getAllCategories(): Resource<CategoriesResponse>
    suspend fun createBid(
        id: Int, title: String,
        author: String,
        pages: Int,
        summary: String
    ): Resource<String>

    suspend fun getBooksByCategories(category: String): Resource<BookResponse>
    suspend fun getBooksByLocation(location: String): Resource<BookResponse>
    suspend fun getSentBids(): Resource<SentBid>
    suspend fun getReceivedBids(): Resource<ReceivedBid>
    suspend fun getLoggedUser(): Resource<UserDetailsResponse>
    suspend fun getBooks(): Resource<BookResponse>
    suspend fun getBookById(id: Int): Resource<BookDetailsResponse>
    suspend fun postBook(
        title: String,
        author: String,
        page: Int,
        category: String,
        location: String,
        summary: String,
        isAvailable: Boolean
    ): Resource<String>

    suspend fun getUserDetails(email: String): Resource<com.example.bookbites.model.user_details.UserDetailsResponse>

    suspend fun acceptBid(id: Int): Resource<String>

    suspend fun getAcceptedBids():Resource<AllAcceptedBidsResponse>

}