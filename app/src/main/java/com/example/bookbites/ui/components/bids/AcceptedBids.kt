package com.example.bookbites.ui.components.bids

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.bookbites.model.accepted_bids.AllAcceptedBidsResponseItem

@Composable
fun AcceptedBidItem(acceptedItems: AllAcceptedBidsResponseItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {},
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Column(modifier = Modifier.padding(start = 1.dp, top = 1.dp)) {

            Text(
                modifier = Modifier.padding(start = 18.dp, top = 10.dp),
                text = "Accepted Bids",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )

            Row(
                modifier = Modifier.padding(top = 25.dp, start = 10.dp)
            ) {

                Image(
                    painter = painterResource(R.drawable.reading),
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(34.dp)
                        .clip(CircleShape)


                )

                Column {
                    Text(
                        modifier = Modifier.padding(start = 15.dp, top = 1.dp),
                        text = "Owner",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    )

                    Text(
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
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                    text = "Title:",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                acceptedItems.bookTitle.let {
                    Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = it.toString(),
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

                acceptedItems.bookAuthor.let {
                    Text(
                        modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                        text = it.toString(),
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

        Row {
            Text(
                modifier = Modifier.padding(start = 15.dp, top = 10.dp),
                text = "Pages : ",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            )
            acceptedItems.bookPage.let {
                Text(
                    modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                    text = it.toString(),
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

            acceptedItems.bookSummary.let {
                Text(
                    modifier = Modifier.padding(start = 3.dp, top = 10.dp, bottom = 10.dp),
                    text = it.toString(),
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
            }

            acceptedItems.createdAt.let {
                Text(
                    modifier = Modifier.padding(start = 3.dp, top = 10.dp, bottom = 10.dp),
                    text = it.toString(),
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



