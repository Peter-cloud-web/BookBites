package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.ui.components.book.Books

@Composable
fun HomeScreen(navController: NavController){
    Books(navController)
}