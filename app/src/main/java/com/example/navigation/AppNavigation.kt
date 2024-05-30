package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookbites.ui.components.book.BookDetails
import com.example.bookbites.ui.components.user.UserProfile
import com.example.bookbites.ui.sceens.AcceptedBidsScreen
import com.example.bookbites.ui.sceens.BidsScreen
import com.example.bookbites.ui.sceens.HomeScreen
import com.example.bookbites.ui.sceens.LoginScreen
import com.example.bookbites.ui.sceens.PostBookScreen
import com.example.bookbites.ui.sceens.ReceivedBidsScreen
import com.example.bookbites.ui.sceens.RegistrationScreen
import com.example.bookbites.ui.sceens.SentBidsScreen
import com.example.bookbites.ui.sceens.SplashScreen
import com.example.bookbites.ui.sceens.UserDetailsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route){
          SplashScreen(navController)
        }
        composable(Screens.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(Screens.Registration.route) {
            RegistrationScreen(navController)
        }

        composable(Screens.HomeScreen.route) {
            HomeScreen(
                navController,
                onBookClicked = { bookId ->
                    navController.navigate(
                        "${Screens.BookDetailScreen.route}/${bookId}"
                    )
                },
                onAvatarClicked = { email ->
                    navController.navigate(
                        "${Screens.UserDetailsScreen.route}/${email}"
                    )
                }

            )
        }

        composable(
            Screens.BookDetailScreen.route + "/{bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt("bookId")
            BookDetails(bookId?.toInt() ?: -1)
        }

        composable(
            Screens.UserDetailsScreen.route + "/{email}",
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val email = navBackStackEntry.arguments?.getString("email")
            UserDetailsScreen(email?.toString()?:"")
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

        composable(Screens.PostBookScreen.route) {
            PostBookScreen(navController)
        }
    }

}