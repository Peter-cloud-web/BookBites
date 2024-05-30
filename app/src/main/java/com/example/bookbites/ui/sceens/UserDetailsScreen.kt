package com.example.bookbites.ui.sceens

import androidx.compose.runtime.Composable
import com.example.bookbites.model.user_details.AssociatedBook
import com.example.bookbites.ui.components.user.UserDetailsItem
import com.example.bookbites.ui.components.user.UserProfile

@Composable
fun UserDetailsScreen(email:String){
    UserProfile(email)
}