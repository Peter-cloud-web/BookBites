package com.example.bookbites.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import kotlinx.serialization.json.Json

class BookBitesKtorClient {

    fun getHttpClient() = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true

            })
        }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i(TAG_KTOR_LOGGER, message)
                    }
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
                }

            }

        }

    companion object {
        private const val TAG_KTOR_LOGGER = "ktor_logger"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status"
    }

}