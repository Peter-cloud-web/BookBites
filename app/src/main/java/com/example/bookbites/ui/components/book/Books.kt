package com.example.bookbites.ui.components.book

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ConnectWithoutContact
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AddAlert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bookbites.R
import com.example.navigation.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Books(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar() },
        bottomBar = { BottomAppBar(navController) }
    ) {



    }


}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBar() {
    MaterialTheme {
        TopAppBar(
            modifier = Modifier.height(150.dp),
            title = {
                Text(
                    text = "BookBites",
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                )
            },

            navigationIcon = {
                IconButton(
                    onClick = {},
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = null)
                }
            },

            actions = {
                IconButton(
                    onClick = {},
                ) {
                    Image(
                        modifier = Modifier.height(30.dp).width(30.dp),
                        painter = painterResource(R.drawable.baseline_filter_alt_24),
                        contentDescription = null
                    )

                }

                Column(

                ) {
                    IconButton(
                        modifier = Modifier.padding(top = 35.dp, end = 20.dp),
                        onClick = {},
                    ) {
                        Icon(Icons.Filled.AccountCircle, contentDescription = null)
                    }

                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Hello, ",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp

                    )

                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Peter",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp

                    )
                }


            },
            backgroundColor = MaterialTheme.colorScheme.onTertiary,
            elevation = AppBarDefaults.TopAppBarElevation,

            )
    }
}

@Composable
fun BottomAppBar(navController: NavController) {
    BottomAppBar(
        actions = {

            IconButton(
                modifier = Modifier.padding(start = 35.dp,),
                onClick = {navController.navigate(Screens.HomeScreen.route)}) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Localized description",
                )
                Text(
                    modifier = Modifier.padding(top = 35.dp,start = 10.dp),
                    text = "Home"
                )
            }
            IconButton(onClick = {navController.navigate(Screens.BidsScreen.route)}) {
                Icon(
                    Icons.Filled.ConnectWithoutContact,
                    contentDescription = "Localized description",
                )
                Text(
                    modifier = Modifier.padding(top = 35.dp,start = 10.dp),
                    text = "Bids"
                )
            }

            IconButton(onClick = { /* do something */ }) {
                Icon(Icons.Filled.Bookmark, contentDescription = "Localized description")
                Text(
                    modifier = Modifier.padding(top = 35.dp,start = 10.dp),
                    text = "Bookmarks"
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    Icons.Rounded.AddAlert,
                    contentDescription = "Notifications",
                )
                Text(
                    modifier = Modifier.padding(top = 35.dp, start = 10.dp),
                    text = "Notifications"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* do something */ },
                contentColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
        containerColor = MaterialTheme.colorScheme.onTertiary
    )
}
