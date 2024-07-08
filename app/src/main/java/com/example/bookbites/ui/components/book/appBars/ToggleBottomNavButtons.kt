package com.example.bookbites.ui.components.book.appBars

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ToggleBottomNavButtons(
    initialColor: Color = Color.DarkGray,
    toggleColor: Color = Color.Black,
    onToggleClick: () -> Unit,
    onNavigationClick: () -> Unit,
    initialIcon: ImageVector,
    toggleIcon: ImageVector,
    contentDescription: String? = null,
    text: String = ""
) {
    var isToggled by remember { mutableStateOf(false) }
    val tint by animateColorAsState(if (isToggled) toggleColor else initialColor)
    val icon = if (isToggled) toggleIcon else initialIcon

    Column(modifier = Modifier.padding(start = 20.dp)) {
        IconButton(
            onClick = {
                isToggled = !isToggled
                onToggleClick()
                if (isToggled) {
                    onNavigationClick()
                }
            },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                modifier = Modifier.size(20.dp),
                tint = tint
            )
        }

        Text(
            modifier = Modifier.padding(top = 5.dp, start = 10.dp),
            text = text
        )

    }

}