package com.example.bookbites.ui.sceens

import Books
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.bookbites.store.SessionManager

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeScreen(
    navController: NavController,
    onBookClicked: (bookId: Int?) -> Unit,
    onAvatarClicked: (email: String?) -> Unit,
    sessionManager: SessionManager
) {
    Books(navController,sessionManager)
}