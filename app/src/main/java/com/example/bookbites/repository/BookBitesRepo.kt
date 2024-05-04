package com.example.bookbites.repository

import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.model.bid.sentBids.SentBid
import com.example.bookbites.model.categories.all_categories.CategoriesResponse
import com.example.bookbites.model.categories.books_categories.CategoryBooksResponse
import com.example.util.Resource

interface BookBitesRepo {

    suspend fun loginUser(userEmail: String,userPassword: String): Resource<AuthResponse>
    suspend fun registerUser(userEmail: String,userName:String,userPassword: String):Resource<String>
    suspend fun getAllCategories():Resource<CategoriesResponse>
    suspend fun getBooksByCategories(category:String):Resource<CategoryBooksResponse>
    suspend fun getSentBids():Resource<SentBid>


}