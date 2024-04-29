package com.example.bookbites.api

import coil.network.HttpException
import com.example.bookbites.model.categories.CategoryBooksItem
import com.example.util.Constants
import com.example.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import java.io.IOException
import javax.inject.Inject

class BookBitesApi @Inject constructor(val httpClient: HttpClient) {

    suspend fun loginUser() {

    }

    suspend fun registerUser() {}

    suspend fun allCategories(category: String): Resource<CategoryBooksItem> {

        return try {
            Resource.Loading(null)
            val response = httpClient.get<CategoryBooksItem> {
                url {
                    protocol = URLProtocol.HTTPS
                    host = Constants.BOOKBITES_API
                    encodedPath = Constants.CATEGORY + "$category"
                }
            }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(null,e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null,e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null,e.localizedMessage ?: "Network error")
        }
    }

    suspend fun getBooksByCategories() {}

    suspend fun postBook() {}

    suspend fun bidForBook() {}

    suspend fun getSentBids() {}

    suspend fun searchBooksByName() {}

    suspend fun getReceivedBids() {}

    suspend fun waitingList() {}

}