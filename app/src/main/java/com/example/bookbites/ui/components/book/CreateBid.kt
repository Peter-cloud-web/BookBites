package com.example.bookbites.ui.components.book

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.Note
import androidx.compose.material.icons.outlined.Pages
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookbites.R
import com.example.bookbites.ui.viewmodels.BooksViewModel
import com.example.bookbites.ui.viewmodels.CreateBidViewModel
import com.example.navigation.Screens

@Composable
fun CreateBid(bookId:Int,navController: NavController) {
    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var page by rememberSaveable { mutableStateOf("") }
    var summary by rememberSaveable { mutableStateOf("") }

    val createBidViewModel: CreateBidViewModel = hiltViewModel()

    val context = LocalContext.current

    Row(modifier = Modifier.padding(top = 35.dp, start = 15.dp).fillMaxSize()) {

        IconButton(
            onClick = { }) {
            androidx.compose.material3.Icon(
                Icons.Outlined.ArrowBackIosNew,
                contentDescription = "Localized description",
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
        }

        Text(
            text = "Create a Bid",
            modifier = Modifier.padding(top = 10.dp, start = 15.dp),
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        )
    }


    Column(
        modifier = Modifier.padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.Center,

        ) {

        Image(
            modifier = Modifier.height(350.dp).width(350.dp).padding(10.dp),
            painter = painterResource(R.drawable.sharebook),
            contentDescription = null
        )

        TextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            label = { Text("Title") },
            leadingIcon = { Icon(Icons.Outlined.Book, "Book", tint = Color.Red) },
            singleLine = true,
        )

        TextField(
            value = author,
            onValueChange = { author = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            label = { Text("Author") },
            leadingIcon = { Icon(Icons.Outlined.Person, "Book", tint = Color.Red) },
            singleLine = true
        )

        TextField(
            value = page,
            onValueChange = { page = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            label = { Text("N.o of pages") },
            leadingIcon = { Icon(Icons.Outlined.Pages, "Book", tint = Color.Red) },
            singleLine = true
        )

        TextField(
            value = summary,
            onValueChange = { summary = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            label = { Text("Why should this book be picked") },
            leadingIcon = { Icon(Icons.Outlined.Note, "Summary", tint = Color.Red) },
            singleLine = true
        )

        Button(
            onClick = {
                createBidViewModel.CreateBid(
                    title = title,
                    author = author,
                    page = page.toInt(),
                    summary = summary
                )
                Toast.makeText(context, "Bid sent successfully", Toast.LENGTH_SHORT).show()
                navController.navigate(Screens.HomeScreen.route)
            },
            colors = ButtonDefaults.buttonColors(Color.Red),
            modifier = Modifier.fillMaxWidth().height(70.dp).padding(10.dp)
        ) {
            androidx.compose.material3.Text(
                "Share Book",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                )
            )
        }
    }

}



