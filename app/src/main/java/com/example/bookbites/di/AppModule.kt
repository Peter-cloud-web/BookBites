package com.example.bookbites.di

import android.content.Context
import com.example.bookbites.api.BookBitesApi
import com.example.bookbites.network.BookBitesKtorClient
import com.example.bookbites.repository.BookBitesRepo
import com.example.bookbites.repository.BookBitesRepoImpl
import com.example.bookbites.store.DataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context):DataStore{
        return DataStore(context)
    }

    @Provides
    @Singleton
    fun provideBookBitesRepository(bookBitesApi: BookBitesApi): BookBitesRepo {
        return BookBitesRepoImpl(bookBitesApi)
    }

}