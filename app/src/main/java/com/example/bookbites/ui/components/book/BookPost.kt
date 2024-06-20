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
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.EventAvailable
import androidx.compose.material.icons.outlined.MyLocation
import androidx.compose.material.icons.outlined.Pages
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Summarize
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
import com.example.navigation.Screens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookPost(navController: NavController) {

    var title by rememberSaveable { mutableStateOf("") }
    var author by rememberSaveable { mutableStateOf("") }
    var page by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var summary by rememberSaveable { mutableStateOf("") }
    var isAvailable by rememberSaveable { mutableStateOf(true) }

    val viewModel: BooksViewModel = hiltViewModel()

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
            text = "Post a Book",
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

        val options = listOf(
            "All",
            "Horror",
            "Biography",
            "History",
            "Self-Help",
            "Cookbooks",
            "Travel",
            "Business",
            "Parenting",
            "Health",
            "Religion",
            "Spirituality",
            "Poetry",
            "Classics",
            "Young Adult",
            "Children's Books",
            "Science",
            "Technology",
            "Art",
            "Music",
            "Sports",
            "Reference",
            "Comics & Graphic Novels"
        )

        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            TextField(
                readOnly = true,
                value = category,
                onValueChange = { },
                label = { Text("Category") },
                leadingIcon = { Icon(Icons.Outlined.Category, "Book", tint = Color.Red) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            category = selectionOption
                            expanded = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }

        val locationOptions = listOf(
            "Baringo",
            "Bomet",
            "Bungoma",
            "Busia",
            "Elgeyo/Marakwet",
            "Embu",
            "Garissa",
            "Homa Bay",
            "Isiolo",
            "Kajiado",
            "Kakamega",
            "Kericho",
            "Kiambu",
            "Kilifi",
            "Kirinyaga",
            "Kisii",
            "Kisumu",
            "Kitui",
            "Kwale",
            "Laikipia",
            "Lamu",
            "Machakos",
            "Makueni",
            "Mandera",
            "Marsabit",
            "Meru",
            "Migori",
            "Mombasa",
            "Murang'a",
            "Nairobi",
            "Nakuru",
            "Nandi",
            "Narok",
            "Nyamira",
            "Nyandarua",
            "Nyeri",
            "Samburu",
            "Siaya",
            "Taita/Taveta",
            "Tana River",
            "Tharaka-Nithi",
            "Trans Nzoia",
            "Turkana",
            "Uasin Gishu",
            "Vihiga",
            "Wajir",
            "West Pokot"
        )

        var expandedLocation by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedLocation,
            onExpandedChange = { expandedLocation = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            TextField(
                readOnly = true,
                value = location,
                onValueChange = { },
                label = { Text("Location") },
                leadingIcon = { Icon(Icons.Outlined.MyLocation, "Location", tint = Color.Red) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedLocation
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedLocation,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                locationOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            location = selectionOption
                            expandedLocation = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }

        val optionsA = listOf("Available", "Unavailable")
        var expandedA by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expandedA,
            onExpandedChange = { expandedA = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
        ) {
            TextField(
                readOnly = true,
                value = isAvailable.toString(),
                onValueChange = { },
                label = { Text("Availability") },
                leadingIcon = { Icon(Icons.Outlined.EventAvailable, "Book", tint = Color.Red) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedA
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedA,
                onDismissRequest = {
                    expandedA = false
                }
            ) {
                optionsA.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            isAvailable = selectionOption == "Available"
                            expandedA = false
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }

        TextField(
            value = summary,
            onValueChange = { summary = it },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            label = { Text("Summary") },
            leadingIcon = { Icon(Icons.Outlined.Summarize, "Book", tint = Color.Red) },
            singleLine = false,
            maxLines = 10
        )

        Button(
            onClick = {
                viewModel.postBook(
                    title = title,
                    author = author,
                    page = page.toInt(),
                    category = category,
                    location = location,
                    summary = summary,
                    isAvailable = isAvailable
                )
                Toast.makeText(context, "Book has been successfully posted!", Toast.LENGTH_SHORT)
                    .show()
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun availabilityDropDownMenu(availability: String) {
    val options = listOf("Available", "Unavailable")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf("") }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = Modifier.padding(10.dp).fillMaxWidth(),
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text("Availability") },
            leadingIcon = { Icon(Icons.Outlined.EventAvailable, "Book", tint = Color.Red) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

fun isAvailable(selectionOption:String):Boolean{
    return selectionOption == "Available"
}

