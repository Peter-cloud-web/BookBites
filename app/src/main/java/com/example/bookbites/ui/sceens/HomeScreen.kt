package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bookbites.model.books.BookResponseItem
import com.example.bookbites.ui.components.book.Books

@Composable
fun HomeScreen(navController: NavController,onBookClicked:(bookId:Int?) -> Unit){
    Books(navController,onBookClicked)
}