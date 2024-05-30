package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.ui.components.book.BookPost

@Composable
fun PostBookScreen(navController: NavController) {
    BookPost(navController)
}