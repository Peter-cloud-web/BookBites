package com.example.bookbites.ui.sceens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.bookbites.R
import com.example.navigation.Screens

@Composable
fun SplashScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onTertiary)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anime))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)

        Text(
            modifier = Modifier.padding(top = 200.dp),
            text = "Welcome to",
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "BookBites",
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 45.sp
            )
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "A platform for books enthusiasts.",
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )

        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "We share and exchange books conveniently",
            style = TextStyle(
                color = Color.Black,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        )

            LottieAnimation(
                composition = composition,
                progress = { logoAnimationState.progress }
            )

            if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
                navController.navigate(Screens.LoginScreen.route)
            }

    }
}