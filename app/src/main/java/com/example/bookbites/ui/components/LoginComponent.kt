package com.example.bookbites.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookbites.R
import com.example.bookbites.ui.viewmodels.LoginViewModel
import com.example.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun Login() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginViewModel: LoginViewModel = hiltViewModel()

    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.height(150.dp).width(150.dp),
            painter = painterResource(R.drawable.reading),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Welcome to BookBites",
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
        )
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Share and exchange books conveniently",
            style = TextStyle(
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp
            )
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 250.dp, end = 20.dp, bottom = 250.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onTertiary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") }
            )

            Spacer(modifier = Modifier.height(10.dp))
            val context = LocalContext.current

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") }
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    loginViewModel.LoginUser(email,password)
                    Toast.makeText(context, "Successfully logged in", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(Color.Red),
                modifier = Modifier.width(300.dp).height(50.dp)
            ) {
                Text(
                    "Login",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.width(300.dp).height(50.dp)
            ) {
                Text(
                    "Register",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                )
            }

            Spacer(modifier = Modifier.height(50.dp))

            ClickableText(
                text = AnnotatedString("Forgot password"),
                onClick = {},
                style = TextStyle(
                    color = Color.Blue,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 15.sp,

                    )
            )

        }

    }

}

@Preview(showBackground = false)
@Composable
fun LoginPreview() {
    Login()
}