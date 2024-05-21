package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookbites.model.bid.sentBids.BiddedBook
import com.example.bookbites.model.bid.sentBids.Book
import com.example.bookbites.ui.components.book.BookDetails
import com.example.bookbites.ui.sceens.AcceptedBidsScreen
import com.example.bookbites.ui.sceens.BidsScreen
import com.example.bookbites.ui.sceens.HomeScreen
import com.example.bookbites.ui.sceens.LoginScreen
import com.example.bookbites.ui.sceens.PostBookScreen
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
                onBookClicked = {bookId ->
                   navController.navigate(
                       "${Screens.BookDetailScreen.route}/${bookId}"
                   )
                }
            )
        }

        composable(Screens.BookDetailScreen.route + "/{bookId}",
        arguments = listOf(navArgument("bookId"){
            type = NavType.IntType
        })
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt("bookId")
            BookDetails(bookId?.toInt()?:-1)
        }

        composable(Screens.BidsScreen.route) {
            BidsScreen()
        }

        composable(Screens.SentBids.route) {
            SentBidsScreen()
        }

        composable(Screens.ReceivedBids.route) {
            ReceivedBidsScreen()
        }

        composable(Screens.AcceptedBids.route) {
            AcceptedBidsScreen()
        }

        composable(Screens.PostBookScreen.route){
            PostBookScreen()
        }
    }

}