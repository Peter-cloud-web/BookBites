package com.example.bookbites.ui.sceens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.ui.components.book.BottomAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BidsScreen(navController: NavController) {
    Scaffold (
        bottomBar = { BottomAppBar(navController) }
    ){}

}