package com.example.bookbites.api

import coil.network.HttpException
import com.example.bookbites.model.User.UserDetailsResponse
import com.example.bookbites.model.User.UserDetailsResponseItem
import com.example.bookbites.model.authentication.AuthResponse
import com.example.bookbites.model.authentication.Login
import com.example.bookbites.model.authentication.Register
import com.example.bookbites.model.bid.receivedBid.ReceivedBid
import com.example.bookbites.model.bid.receivedBid.ReceivedBidItem
import com.example.bookbites.model.bid.sentBids.SentBid
import com.example.bookbites.model.bid.sentBids.SentBidItem
import com.example.bookbites.model.books.Book
import com.example.bookbites.model.books.BookResponse
import com.example.bookbites.model.books.BookResponseItem
import com.example.bookbites.model.books.PostBookResponse
import com.example.bookbites.model.books.bookDetails.BookDetailsResponse
import com.example.bookbites.model.categories.all_categories.CategoriesResponse
import com.example.bookbites.model.categories.books_categories.CategoryBooksResponse
import com.example.bookbites.store.SessionManager
import com.example.util.Constants
import com.example.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import kotlinx.coroutines.flow.firstOrNull
import java.io.IOException
import javax.inject.Inject

class BookBitesApi @Inject constructor(
    val httpClient: HttpClient,
    var sessionManager: SessionManager
) {

    suspend fun loginUser(userEmail: String, userPassword: String): Resource<String> {

        return try {
            Resource.Loading(null)
            val authResponse = httpClient.post<AuthResponse>() {
                url {
                    protocol = URLProtocol.HTTP
                    host = Constants.BOOKBITES_API
                    encodedPath = Constants.LOGIN
                }
                contentType(ContentType.Application.Json)
                body = Login(userEmail, userPassword)
            }
            if (authResponse.success) {
                val token = authResponse.message
                Resource.Success(token)
            } else {
                Resource.Error(null, "Login failed:${authResponse.message}")

            }
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun registerUser(
        userEmail: String,
        userName: String,
        userPassword: String
    ): Resource<String> {
        return try {
            Resource.Loading(null)
            val authResponse = httpClient.post<AuthResponse>() {
                url {
                    protocol = URLProtocol.HTTP
                    host = Constants.BOOKBITES_API
                    encodedPath = Constants.REGISTER
                }
                contentType(ContentType.Application.Json)
                body = Register(
                    userEmail = userEmail,
                    userName = userName,
                    userPassword = userPassword
                )
            }

            if (authResponse.success) {
                val token = authResponse.message
                Resource.Success(token)
            } else {
                Resource.Error(null, "Registration failed:${authResponse.message}")

            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun postBook(
        title: String,
        author: String,
        page: Int,
        category: String,
        summary: String,
        isAvailable: Boolean
    ): Resource<String> {

        return try {
            val token = sessionManager.getToken.firstOrNull()
            if (token != null) {
                Resource.Loading(null)
                val response = httpClient.post<PostBookResponse>() {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.CREATE_BOOK_POST
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                    contentType(ContentType.Application.Json)
                    body = Book(
                        author = author,
                        category = category,
                        isAvailable = isAvailable,
                        page = page,
                        summary = summary,
                        title = title
                    )
                }
                if (response != null) {
                    Resource.Success(response.message)
                } else {
                    Resource.Error(data = "null", message = "No value found")
                }

            } else {
                Resource.Error(null, "Token not found")
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }

    }

    suspend fun allCategories(): Resource<CategoriesResponse> {

        return try {
            Resource.Loading(null)
            val response = httpClient.get<CategoriesResponse> {
                url {
                    protocol = URLProtocol.HTTP
                    host = Constants.BOOKBITES_API
                    encodedPath = Constants.ALL_CATEGORIES
                }
            }
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun getBooksByCategories(category: String): Resource<CategoryBooksResponse> {

        return try {
            Resource.Loading(null)
            val allBooksByCategory = httpClient.get<CategoryBooksResponse> {
                url {
                    protocol = URLProtocol.HTTP
                    host = Constants.BOOKBITES_API
                    encodedPath = Constants.CATEGORY + "$category"
                }
            }
            Resource.Success(allBooksByCategory)
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "An unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }

    }

    suspend fun postBook() {}

    suspend fun bidForBook() {}

    suspend fun getSentBids(): Resource<SentBid> {

        return try {
            val token = sessionManager.getToken.firstOrNull()

            if (token != null) {

                Resource.Loading(null)
                val sentBids = httpClient.get<List<SentBidItem>>() {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.SENT_BIDS
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
                Resource.Success(SentBid(sentBids))
            } else {
                Resource.Error(null, "Token not found")
            }
        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "A unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun getLoggedUser(): Resource<UserDetailsResponse> {
        return try {
            val token = sessionManager.getToken.firstOrNull()

            if (token != null) {
                Resource.Loading(null)
                val sentBids = httpClient.get<List<UserDetailsResponseItem>>() {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.USERDETAILS
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
                Resource.Success(UserDetailsResponse(sentBids))
            } else {
                Resource.Error(null, "Token not found")
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "A unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun getAllBooks(): Resource<BookResponse> {
        return try {
            val token = sessionManager.getToken.firstOrNull()

            if (token != null) {
                Resource.Loading(null)
                val response = httpClient.get<List<BookResponseItem>>() {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.BOOKS
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
                Resource.Success(BookResponse(response))
            } else {
                Resource.Error(null, "Token not found")
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "A unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun getBookById(id: Int): Resource<BookDetailsResponse> {
        return try {
            val token = sessionManager.getToken.firstOrNull()

            if (token != null) {
                Resource.Loading(null)
                val response = httpClient.get<BookDetailsResponse>() {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.BOOKDETAIL + "/${id}"
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
                Resource.Success(response)
            } else {
                Resource.Error(null, "Token not found")
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "A unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }

    suspend fun searchBooksByName() {}

    suspend fun getReceivedBids(): Resource<ReceivedBid> {
        return try {
            val token = sessionManager.getToken.firstOrNull()
            if (token != null) {
                Resource.Loading(null)
                val receivedBids = httpClient.get<List<ReceivedBidItem>> {
                    url {
                        protocol = URLProtocol.HTTP
                        host = Constants.BOOKBITES_API
                        encodedPath = Constants.RECEIVED_BIDS
                    }
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
                Resource.Success(ReceivedBid(receivedBids))
            } else {
                Resource.Error(null, "Token not found")
            }

        } catch (e: Exception) {
            Resource.Error(null, e.localizedMessage ?: "A unexpected error occurred")
        } catch (e: IOException) {
            Resource.Error(null, e.localizedMessage ?: "Network server error")
        } catch (e: HttpException) {
            Resource.Error(null, e.localizedMessage ?: "Network error")
        }
    }


    suspend fun waitingList() {}

}