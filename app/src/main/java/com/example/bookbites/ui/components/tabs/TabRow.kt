package com.example.bookbites.ui.components.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.bookbites.model.bid.sentBids.BiddedBook
import com.example.bookbites.model.bid.sentBids.Book
import com.example.bookbites.ui.sceens.AcceptedBidsScreen
import com.example.bookbites.ui.sceens.ReceivedBidsScreen
import com.example.bookbites.ui.sceens.SentBidsScreen

@Composable
fun TabScreen() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Sent Bids", "Received Bids","Accepted Bids")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex,modifier = Modifier.fillMaxWidth().padding(top = 60.dp), containerColor = Color.White) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    icon = {
                        when(index){
                            0 -> Icon(imageVector = Icons.Default.Send, contentDescription = null,tint = Color.Red)
                            1 -> Icon(imageVector = Icons.Default.Mail, contentDescription = null,tint = Color.Red)
                            2 -> Icon(imageVector = Icons.Default.Verified, contentDescription = null,tint = Color.Red)
                            else -> null
                        }
                    },

                )
            }
        }
        when (tabIndex) {
            0 -> SentBidsScreen()
            1 -> ReceivedBidsScreen()
            2 -> AcceptedBidsScreen()
        }
    }
}