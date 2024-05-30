package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.ui.components.auth.Register


@Composable
fun RegistrationScreen(navController: NavController){
    Register(navController)
}