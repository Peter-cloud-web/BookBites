package com.example.bookbites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.bookbites.model.bid.receivedBid.ReceivedBid
import com.example.bookbites.ui.components.bids.ReceiveBiddedBookItem
import com.example.bookbites.ui.theme.BookBitesTheme
import com.example.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookBitesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onTertiary

                ) {
//                    AppNavigation()
                    ReceiveBiddedBookItem()
                }
            }
        }
    }
}
