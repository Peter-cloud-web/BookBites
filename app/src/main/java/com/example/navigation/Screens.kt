package com.example.navigation

sealed class Screens(val route: String) {
    object Splash : Screens("splash_screen")
    object LoginScreen : Screens("login")
    object Registration : Screens("registration")
    object HomeScreen : Screens("home")
    object BookDetailScreen:Screens("BookDetails")
    object BidsScreen : Screens("bids")
    object ReceivedBids : Screens("receivedBids")
    object SentBids : Screens("sentBids")
    object AcceptedBidsScreen:Screens("AcceptedBids")
    object PostBookScreen:Screens("postBook")
    object UserDetailsScreen:Screens("UserDetailsScreen")
    object CreateBidScreen:Screens("CreateBidScreen")
}
