package com.example.bookbites.ui.components.book.appBars

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.navigation.Screens

@Composable
fun BottomAppBar(navController: NavController) {

    androidx.compose.material3.BottomAppBar(
        actions = {

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = { navController.navigate(Screens.HomeScreen.route) },
                initialIcon = Icons.Outlined.Home,
                toggleIcon = Icons.Filled.Home,
                contentDescription = null,
                text = "Home"
            )

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = { navController.navigate(Screens.BidsScreen.route) },
                initialIcon = Icons.Outlined.Sell,
                toggleIcon = Icons.Filled.Sell,
                contentDescription = null,
                text = "Bids"
            )
            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = {},
                initialIcon = Icons.Outlined.BookmarkBorder,
                toggleIcon = Icons.Filled.Bookmark,
                contentDescription = null,
                text = "Bookmarks"
            )

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = {},
                initialIcon = Icons.Outlined.PeopleOutline,
                toggleIcon = Icons.Filled.People,
                contentDescription = null,
                text = "Communities"
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.PostBookScreen.route) },
                contentColor = Color.Red,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
        containerColor = MaterialTheme.colorScheme.onTertiary
    )
}
