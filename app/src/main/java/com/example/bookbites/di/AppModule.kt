package com.example.bookbites.di

import com.example.bookbites.network.BookBitesKtorClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getHttpClient(): HttpClient {
        return BookBitesKtorClient().getHttpClient()
    }
}