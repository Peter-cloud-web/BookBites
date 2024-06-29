package com.example.bookbites.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector


data class NavigationItems(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun NavigationDrawer() {

    val items = listOf(
        NavigationItems(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home
        ),

        NavigationItems(
            title = "Bids",
            selectedIcon = Icons.Filled.Sell,
            unselectedIcon = Icons.Outlined.Sell
        ),

        NavigationItems(
            title = "Bookmarks",
            selectedIcon = Icons.Filled.Bookmark,
            unselectedIcon = Icons.Outlined.Bookmark
        ),

        NavigationItems(
            title = "Communities",
            selectedIcon = Icons.Filled.People,
            unselectedIcon = Icons.Outlined.People
        ),

        NavigationItems(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings
        )
    )

    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

}