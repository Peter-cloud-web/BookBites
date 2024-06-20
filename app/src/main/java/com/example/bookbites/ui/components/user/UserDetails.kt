package com.example.bookbites.ui.components.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.bookbites.model.user_details.AssociatedBook

@Composable
fun UserDetailsItem() {

    Column(modifier = Modifier.fillMaxWidth().padding(top = 30.dp, start = 10.dp).size(40.dp)) {
        Row() {

            IconButton(
                onClick = {},
            ) {
                Icon(
                    Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            Text(
                text = "User Details",
                modifier = Modifier.padding(top = 10.dp),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onTertiary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            border = BorderStroke(0.dp, color = Color.LightGray)
        ) {

            Row(modifier = Modifier.padding(start = 360.dp)) {
                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 10.dp, start = 30.dp, bottom = 40.dp)) {

                Image(
                    painter = painterResource(R.drawable.peter),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(34.dp)
                        .clip(CircleShape)

                )


                Column {

                    Text(
                        text = "Peter",
                        modifier = Modifier.padding(start = 10.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 18.sp
                    )


                    Text(
                        text = "20 posts",
                        modifier = Modifier.padding(start = 10.dp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                }


            }
        }


        Spacer(modifier = Modifier.size(40.dp))

        Text(
            text = "Associated Books",
            modifier = Modifier.padding(start = 20.dp),
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )


    }
}

@Composable
fun AssociatedBooksItem(associatedBook: AssociatedBook) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable {},
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = BorderStroke(1.dp, Color.LightGray),
    ) {
        Column(modifier = Modifier.padding(start = 1.dp, top = 1.dp, end = 10.dp)) {

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
                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 15.dp, top = 1.dp),
                        text = "Peter",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )


                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 15.dp, top = 8.dp),
                        text = "",
                        style = TextStyle(
                            color = Color.Black,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            Row {
                androidx.compose.material.Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Book :",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                associatedBook.let {
                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = it.title,
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
                androidx.compose.material.Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Written by ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                associatedBook.let {
                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = it.title,
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
                androidx.compose.material.Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Category : ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                associatedBook.let {
                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = it.category,
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
                androidx.compose.material.Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Summary : ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                associatedBook.let {
                    androidx.compose.material.Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp, bottom = 10.dp),
                        text = it.summary,
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

    }
}

