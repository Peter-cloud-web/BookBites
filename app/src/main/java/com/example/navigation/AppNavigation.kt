package com.example.navigation

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.components.book.BookDetails
import com.example.bookbites.ui.sceens.AcceptedBidsScreen
import com.example.bookbites.ui.sceens.BidsScreen
import com.example.bookbites.ui.sceens.CreateBidScreen
import com.example.bookbites.ui.sceens.HomeScreen
import com.example.bookbites.ui.sceens.LoginScreen
import com.example.bookbites.ui.sceens.PostBookScreen
import com.example.bookbites.ui.sceens.ReceivedBidsScreen
import com.example.bookbites.ui.sceens.RegistrationScreen
import com.example.bookbites.ui.sceens.SentBidsScreen
import com.example.bookbites.ui.sceens.SplashScreen
import com.example.bookbites.ui.sceens.UserDetailsScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavigation(sessionManager: SessionManager) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route
    ) {
        composable(Screens.Splash.route) {
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
                },
                sessionManager

            )
        }

        composable(
            Screens.BookDetailScreen.route + "/{bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt("bookId")
            BookDetails(bookId?.toInt() ?: -1, onBidClicked = { bookId ->
                navController.navigate("${Screens.CreateBidScreen.route}/${bookId}")
            })
        }

        composable(
            Screens.UserDetailsScreen.route + "/{email}",
            arguments = listOf(navArgument("email") {
                type = NavType.StringType
            })
        ) { navBackStackEntry ->
            val email = navBackStackEntry.arguments?.getString("email")
            UserDetailsScreen(email?.toString() ?: "")
        }

        composable(
            Screens.CreateBidScreen.route + "/{bookId}",
            arguments = listOf(navArgument("bookId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val bookId = navBackStackEntry.arguments?.getInt("bookId")
            bookId?.toInt()?.let { id -> CreateBidScreen(navController, id) }
        }



        composable(Screens.BidsScreen.route) { backStackEntry ->
            val savedStateHandle = backStackEntry.savedStateHandle
            BidsScreen(savedStateHandle, onAcceptedBid = {bidId ->
                navController.navigate("${Screens.AcceptedBidsScreen.route}/${bidId}")
            })
        }

        composable(Screens.SentBids.route) {
            SentBidsScreen()
        }

        composable(Screens.ReceivedBids.route) {
            val context = LocalContext.current
            ReceivedBidsScreen(onAcceptedBidClick = { bidId ->
                navController.navigate("${Screens.AcceptedBidsScreen.route}/${bidId}")
            })
        }

        composable(
            Screens.AcceptedBidsScreen.route + "/{bidId}",
            arguments = listOf(navArgument("bidId") {
                type = NavType.IntType
            })
        ) { navBackStackEntry ->
            val bidId = navBackStackEntry.arguments?.getInt("bidId")
            AcceptedBidsScreen(bidId?.toInt() ?: -1)
        }

        composable(Screens.PostBookScreen.route) {
            PostBookScreen(navController)
        }
    }

}