package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookbites.ui.components.book.BookDetails
import com.example.bookbites.ui.sceens.BidsScreen
import com.example.bookbites.ui.sceens.HomeScreen
import com.example.bookbites.ui.sceens.LoginScreen
import com.example.bookbites.ui.sceens.ReceivedBidsScreen
import com.example.bookbites.ui.sceens.RegistrationScreen
import com.example.bookbites.ui.sceens.SentBidsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.LoginScreen.route
    ) {
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(Screens.Registration.route) {
            RegistrationScreen()
        }

        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController,
                onBookClicked = {id ->
                   navController.navigate(
                       "${Screens.BookDetailScreen.route}/${id}"
                   )
                }
            )
        }

        composable(Screens.BookDetailScreen.route) {
            BookDetails()
        }

        composable(Screens.BidsScreen.route) {
            BidsScreen(navController)
        }

        composable(Screens.SentBids.route) {
            SentBidsScreen()
        }

        composable(Screens.ReceivedBids.route) {
            ReceivedBidsScreen()
        }
    }

}