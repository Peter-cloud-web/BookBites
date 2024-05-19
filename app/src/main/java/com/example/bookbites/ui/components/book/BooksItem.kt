package com.example.bookbites.ui.components.book

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
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
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun bookItem(books: BookResponseItem, navigationRoute: String, onBookClicked: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onBookClicked(books.bookId) },
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Column(modifier = Modifier.padding(start = 1.dp, top = 1.dp)) {

            Row(
                modifier = Modifier.padding(top = 25.dp, start = 10.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.peter),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(34.dp)
                        .clip(CircleShape)


                )

                Column {
                    books.owner.let { owner ->
                        Text(
                            modifier = Modifier.padding(start = 15.dp, top = 1.dp),
                            text = owner,
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        )
                    }

                    books.timeOfCreation.let { date ->

                        Text(

                            modifier = Modifier.padding(start = 15.dp, top = 8.dp),
                            text = "${convertLongToTime(date)}",
                            style = TextStyle(
                                color = Color.Black,
                                fontFamily = FontFamily.Monospace,
                                fontWeight = FontWeight.Medium,
                                fontSize = 11.sp
                            )
                        )
                    }
                }


                IconButton(
                    onClick = {},
                    modifier = Modifier.padding(start = 250.dp)
                ) {
                    Icon(
                        Icons.Filled.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp),
                        tint = Color.DarkGray
                    )
                }
            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Book :",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                books.book.title.let { title ->
                    Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = title.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Written by ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
                books.book.author.let { author ->
                    Text(
                        modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                        text = author.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Category : ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
                books.book.category.let { category ->
                    Text(
                        modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                        text = category.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }

            }

            Row {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Summary : ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
                books.book.summary.let { summary ->
                    Text(
                        modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                        text = summary.toString(),
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
        Row(modifier = Modifier.padding(start = 5.dp, top = 25.dp)) {
            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.BookmarkBorder,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.DarkGray
                )
            }

            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.DarkGray
                )
            }

            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Filled.FastForward,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.DarkGray
                )
            }
        }
    }
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}
