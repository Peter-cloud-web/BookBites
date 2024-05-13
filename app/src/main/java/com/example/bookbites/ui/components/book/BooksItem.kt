package com.example.bookbites.ui.components.book

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbites.R
import com.example.bookbites.model.books.BookResponseItem

@Composable
fun bookItem(books: BookResponseItem, navigationRoute:String, onBookClicked: Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(modifier = Modifier.padding(start = 1.dp, top = 1.dp)) {

            Row(
                modifier = Modifier.padding(top = 10.dp, start = 10.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.peter),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(34.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Green, CircleShape)

                )
                books.owner.let { owner ->
                    Text(
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp),
                        text = owner.toUpperCase(),
                        style = TextStyle(
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )
                }

            }
            Row {
                Text(
                    modifier = Modifier.padding(start = 65.dp),
                    text = "Title",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                )

                books.book.title.let { title ->
                    Text(
                        modifier = Modifier.padding(start = 55.dp),
                        text = title.toString(),
                        style = TextStyle(
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 65.dp),
                    text = "Author",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                )
                books.book.author.let { author ->
                    Text(
                        modifier = Modifier.padding(start = 45.dp),
                        text = author.toString(),
                        style = TextStyle(
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 65.dp),
                    text = "Category",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                )
                books.book.category.let { category ->
                    Text(
                        modifier = Modifier.padding(start = 40.dp),
                        text = category.toString(),
                        style = TextStyle(
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 65.dp),
                    text = "Description",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )
                )
                books.book.summary.let { summary ->
                    Text(
                        modifier = Modifier.padding(start = 30.dp),
                        text = summary.toString(),
                        style = TextStyle(
                            color = Color.Gray,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(start = 5.dp, top = 5.dp)) {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.Bookmark,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
            }

            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Gray
                )
            }
            books.timeOfCreation.let { date ->

                Text(
                    modifier = Modifier.padding(start = 200.dp, top = 30.dp),
                    text = "posted on ${date.toString()}",
                    style = TextStyle(
                        color = Color.Gray,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp
                    )
                )
            }
        }


    }
}
