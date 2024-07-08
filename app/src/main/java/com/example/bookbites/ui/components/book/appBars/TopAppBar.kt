package com.example.bookbites.ui.components.book.appBars

import FilterLocationChips
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.components.book.filters.FilterCategoryChips
import com.example.navigation.Screens
import kotlinx.coroutines.launch
import logoutUser

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBar(
    navController: NavController,
    sessionManager: SessionManager,
    onLocationSelected: (String) -> Unit,
    onCategorySelected: (String?) -> Unit,
    onOpenDrawer: () -> Unit
) {
    var showDropdown by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    MaterialTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.onTertiary)) {
            androidx.compose.material.TopAppBar(
                title = {
                    Text(
                        text = "BookBites",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp
                    )
                },
                modifier = Modifier.height(100.dp),

                navigationIcon = {

                    IconButton(
                        onClick = {
                            onOpenDrawer
                        }
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },

                actions = {
                    IconButton(
                        onClick = {
                        },
                    ) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = null,
                            tint = Color.Black
                        )

                    }

                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            Icons.Default.MyLocation,
                            contentDescription = null,
                            tint = Color.Black
                        )

                    }

                    Column(

                    ) {

                        IconButton(
                            modifier = Modifier.padding(end = 20.dp),
                            onClick = {
                                showDropdown = true
                            },
                        ) {
                            Icon(Icons.Filled.AccountCircle, contentDescription = null)
                        }

                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }) {
                            DropdownMenuItem(
                                text = { Text("Logout") },
                                onClick = {
                                    scope.launch {
                                        logoutUser(sessionManager)
                                    }
                                    navController.navigate(Screens.LoginScreen.route)
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Logout,
                                        contentDescription = null
                                    )
                                }
                            )

                        }
                    }

                },
                backgroundColor = MaterialTheme.colorScheme.onTertiary,
                elevation = AppBarDefaults.TopAppBarElevation,
            )

            Text(
                text = "Filter by Categories",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp,top =5.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                )
            )
            FilterCategoryChips(onCategorySelected = onCategorySelected)

            Text(
                text = "Filter by Location",
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                )
            )

            FilterLocationChips(onLocationSelected = onLocationSelected)
        }
    }
}