package com.example.bookbites.ui.components.book

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.Pages
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbites.R
import com.example.bookbites.model.books.bookDetails.BookDetailsResponse

@Composable
fun BookDetails(books: BookDetailsResponse) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.height(450.dp).width(450.dp),
            painter = painterResource(R.drawable.knowledge),
            contentDescription = null,
            alignment = Alignment.CenterStart
        )

        books.book.title.let { title ->
            Text(
                title.toString(),
                modifier = Modifier.padding(start = 30.dp, bottom = 20.dp, end = 10.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )
        }

        books.book.author.let { author ->
            Text(
                author.toString(),
                modifier = Modifier.padding(start = 30.dp, bottom = 10.dp, top = 0.dp, end = 10.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )
        }


        books.owner.let { owner ->
            Text(
                owner.toString(),
                modifier = Modifier.padding(start = 30.dp, bottom = 20.dp, end = 10.dp),
                style = TextStyle(
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )
        }


        Row(
            modifier = Modifier.padding(start = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(10.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        Icons.Outlined.Category,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Red
                    )

                    books.book.category.let { category ->
                        Text(
                            category.toString(),
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        )
                    }

                }

            }



            Card(
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(10.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        Icons.Outlined.Pages,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Red
                    )

                    books.book.page.let { page ->
                        Text(
                            page.toString(),
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        )
                    }

                }

            }


            Card(
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(10.dp),
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onTertiary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        Icons.Outlined.EventAvailable,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Red
                    )
                    if (books.book.isAvailable) {
                        Text(
                            "Book is available",
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = Color.Green
                            )
                        )
                    } else {
                        Text(
                            "Book is Unavailable",
                            style = TextStyle(
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                        )
                    }
                }
            }
        }
    }


    Card(
        modifier = Modifier
            .padding(10.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {


        Text(
            "Description",
            modifier = Modifier.padding(15.dp),
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 23.sp,
                color = Color.DarkGray
            )
        )
    }

    books.book.summary.let { summary ->
        Text(
            summary.toString(),
            modifier = Modifier.padding(12.dp),
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                color = Color.LightGray,
            )
        )
    }

    books.timeOfCreation.let {date ->
        Text(
            "Posted : ${date.toString()}",
            modifier = Modifier.padding(12.dp),
            style = TextStyle(
                textAlign = TextAlign.End,
                fontFamily = FontFamily.Serif,
                fontSize = 15.sp,
                color = Color.Black,
            )
        )
    }

    Button(
        onClick = {

        },
        colors = ButtonDefaults.buttonColors(Color.Red),
        modifier = Modifier.fillMaxWidth().height(70.dp).padding(10.dp)
    ) {
        Text(
            "Send a Bid",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
            )
        )
    }

}
