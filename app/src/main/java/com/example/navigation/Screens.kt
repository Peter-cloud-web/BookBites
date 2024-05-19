package com.example.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens("login")
    object Registration : Screens("registration")
    object HomeScreen : Screens("home")
    object BookDetailScreen:Screens("BookDetails")
    object BidsScreen : Screens("bids")
    object ReceivedBids : Screens("receivedBids")
    object SentBids : Screens("sentBids")
    object PostBookScreen:Screens("postBook")
}
