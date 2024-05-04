package com.example.bookbites.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val JWT_TOKEN = "token"

class DataStore @Inject constructor(@ApplicationContext private val context: Context) {

    companion object{
        private val Context.dataStore:DataStore<Preferences> by preferencesDataStore("token")
        val JWT_TOKEN = stringPreferencesKey("jwt_token")
    }

    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
           preferences[JWT_TOKEN]?:""
        }

    suspend fun saveToken(token:String){
        context.dataStore.edit {preferences ->
            preferences[JWT_TOKEN] = token
        }
    }
}