package com.example.bookbites.ui.components.bids

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbites.R
import com.example.bookbites.model.bid.receivedBid.BiddedBook
import com.example.bookbites.model.bid.receivedBid.Book
import com.example.bookbites.model.bid.receivedBid.ReceivedBid
import com.example.bookbites.model.bid.receivedBid.ReceivedBidItem


@Composable
fun ReceiveBiddedBookItem(biddedBook: BiddedBook) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
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

            Text(
                modifier = Modifier.padding(start = 18.dp, top = 10.dp),
                text = "Your Book",
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
                    painter = painterResource(R.drawable.peter),
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
                    text = "Book :",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )

                biddedBook.title.let { biddedBook ->
                    Text(
                        modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                        text = biddedBook.toString(),
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
                text = "Written by ",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            )
            biddedBook.let { biddedBook ->
                Text(
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                    text = biddedBook.author.toString(),
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

            biddedBook.let { biddedBook ->
                Text(
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp),
                    text = biddedBook.category.toString(),
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


            biddedBook.let {
                Text(
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp, bottom = 5.dp),
                    text = it.summary.toString(),
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


@Composable
fun ReceiveBidderBook(book: Book,onAcceptedBidClick:(bidId:Int) -> Unit,receivedBid: ReceivedBidItem) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp)
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
        Column(modifier = Modifier.padding(start = 1.dp, top = 1.dp)) {

            Text(
                modifier = Modifier.padding(start = 18.dp, top = 10.dp),
                text = "Received Bid",
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
                        modifier = Modifier.padding(start = 15.dp, top = 5.dp),
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

                book.title.let {
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

                book.author.let {
                    Text(
                        modifier = Modifier.padding(start = 3.dp, top = 10.dp),
                        text = it,
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
                    text = "Pages : ",
                    style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                )
                book.pages.let {
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

                book.summary.let {
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

            Button(
                onClick = {
                    onAcceptedBidClick(receivedBid.bidId)
                    Toast.makeText(context, "You accepted a bid", Toast.LENGTH_SHORT).show()
                          },
                colors = ButtonDefaults.buttonColors(Color.Green),
                modifier = Modifier.width(130.dp).height(30.dp).padding(top = 3.dp,start = 10.dp)

                ) {
                androidx.compose.material3.Text(
                    "Accept Bid",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))

        }

    }
}



