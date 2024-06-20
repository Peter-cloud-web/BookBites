package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.ui.components.book.CreateBid


@Composable
fun CreateBidScreen(navController: NavController,bookId:Int) {
    CreateBid(navController = navController, bookId = bookId)
}