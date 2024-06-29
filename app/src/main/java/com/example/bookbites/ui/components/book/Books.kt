import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.PeopleOutline
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bookbites.model.books.BookResponseItem
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.components.book.bookItem
import com.example.bookbites.ui.uistates.BookStates
import com.example.bookbites.ui.viewmodels.BooksViewModel
import com.example.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Books(
    navController: NavController,
    sessionManager: SessionManager
) {

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var selectedLocation by remember { mutableStateOf<String?>(null) }

    val booksViewModel: BooksViewModel = hiltViewModel()
    val books = booksViewModel.book.collectAsStateWithLifecycle()

    val selectedCategoryBooks = booksViewModel.selectedCategoryBooks.collectAsStateWithLifecycle()
    val selectedLocationBooks = booksViewModel.selectedLocationBooks.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                navController,
                sessionManager,
                onLocationSelected = { location ->
                    selectedLocation = location
                    if (location != null) {
                        booksViewModel.getBooksByLocation(location)
                    } else {
                        booksViewModel.getBooks()
                    }
                },
                onCategorySelected = { category ->
                    selectedCategory = category
                    if (category != null) {
                        booksViewModel.getBooksByCategory(category)
                    } else {
                        booksViewModel.getBooks()
                    }
                }
            )
        },
        bottomBar = { BottomAppBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {

                if (selectedCategory != null) {
                    Text(
                        text = "Selected Category: $selectedCategory",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                    SelectedCategoryBooks(
                        booksState = selectedCategoryBooks.value,
                        navigationRoute = navController.toString(),
                        onBookClicked = { bookId ->
                            if (bookId != null) {
                                navController.navigate("BookDetails/${bookId}")
                            }
                        },
                        onAvatarClicked = { email ->
                            if (email != null) {
                                navController.navigate("UserDetailsScreen/${email}")
                            }
                        }
                    )
                } else if (selectedLocation != null) {
                    Text(
                        text = "Selected Location: $selectedLocation",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                    SelectedLocationBooks(
                        booksState = selectedLocationBooks.value,
                        navigationRoute = navController.toString(),
                        onBookClicked = { bookId ->
                            if (bookId != null) {
                                navController.navigate("BookDetails/${bookId}")
                            }
                        },
                        onAvatarClicked = { email ->
                            if (email != null) {
                                navController.navigate("UserDetailsScreen/${email}")
                            }
                        }
                    )
                } else {

                    books.value.let { states ->
                        when {

                            states.isLoading -> {
                                CircularProgressIndicator()
                            }

                            states.isSuccess != null -> {
                                states.isSuccess.books?.let { bookList ->
                                    BooksList(
                                        books = bookList,
                                        navigationRoute = "BookDetails",
                                        onBookClicked = { bookId ->
                                            if (bookId != null) {
                                                navController.navigate("BookDetails/${bookId}")
                                            }
                                        },
                                        onAvatarClicked = { email ->
                                            if (email != null) {
                                                navController.navigate("UserDetailsScreen/${email}")
                                            }
                                        }
                                    )
                                }
                            }

                            states.error != null -> "An unexpected error occcurred"

                            else -> {}
                        }
                    }
                }

            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBar(
    navController: NavController,
    sessionManager: SessionManager,
    onLocationSelected: (String) -> Unit,
    onCategorySelected: (String?) -> Unit,
) {
    var showDropdown by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    MaterialTheme {
        Column(modifier = Modifier.background(MaterialTheme.colorScheme.onTertiary)) {
            TopAppBar(
                title = {
                    Text(
                        text = "BookBites",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 30.sp
                    )
                },

                modifier = Modifier.height(100.dp),

                navigationIcon = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = null)
                    }
                },

                actions = {
                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = null,
                            tint = Color.Black
                        )

                    }

                    IconButton(
                        onClick = {},
                    ) {
                        Icon(
                            Icons.Default.MyLocation,
                            contentDescription = null,
                            tint = Color.Black
                        )

                    }

                    Column(

                    ) {

                        IconButton(
                            modifier = Modifier.padding(end = 20.dp),
                            onClick = {
                                showDropdown = true
                            },
                        ) {
                            Icon(Icons.Filled.AccountCircle, contentDescription = null)
                        }

                        DropdownMenu(
                            expanded = showDropdown,
                            onDismissRequest = { showDropdown = false }) {
                            DropdownMenuItem(
                                text = { Text("Logout") },
                                onClick = {
                                    scope.launch {
                                        logoutUser(sessionManager)
                                    }
                                    navController.navigate(Screens.LoginScreen.route)
                                },
                                leadingIcon = {
                                    Icon(
                                        Icons.Outlined.Logout,
                                        contentDescription = null
                                    )
                                }
                            )

                        }
                    }

                },
                backgroundColor = MaterialTheme.colorScheme.onTertiary,
                elevation = AppBarDefaults.TopAppBarElevation,
            )
            FilterCategoryChips(onCategorySelected = onCategorySelected)

            FilterLocationChips(onLocationSelected = onLocationSelected)
        }
    }
}

suspend fun logoutUser(sessionManager: SessionManager) {
    withContext(Dispatchers.IO) {
        sessionManager.clearSession()
    }
}

@Composable
fun BottomAppBar(navController: NavController) {

    BottomAppBar(
        actions = {

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = { navController.navigate(Screens.HomeScreen.route) },
                initialIcon = Icons.Outlined.Home,
                toggleIcon = Icons.Filled.Home,
                contentDescription = null,
                text = "Home"
            )

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = { navController.navigate(Screens.BidsScreen.route) },
                initialIcon = Icons.Outlined.Sell,
                toggleIcon = Icons.Filled.Sell,
                contentDescription = null,
                text = "Bids"
            )
            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = {},
                initialIcon = Icons.Outlined.BookmarkBorder,
                toggleIcon = Icons.Filled.Bookmark,
                contentDescription = null,
                text = "Bookmarks"
            )

            ToggleBottomNavButtons(
                initialColor = Color.DarkGray,
                toggleColor = Color.Red,
                onToggleClick = {},
                onNavigationClick = {},
                initialIcon = Icons.Outlined.PeopleOutline,
                toggleIcon = Icons.Filled.People,
                contentDescription = null,
                text = "Communities"
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screens.PostBookScreen.route) },
                contentColor = Color.Red,
                elevation = FloatingActionButtonDefaults.elevation()
            ) {
                Icon(Icons.Filled.Add, "Localized description")
            }
        },
        containerColor = MaterialTheme.colorScheme.onTertiary
    )
}

@Composable
fun SelectedCategoryBooks(
    booksState: BookStates,
    navigationRoute: String,
    onBookClicked: (bookId: Int?) -> Unit,
    onAvatarClicked: (email: String?) -> Unit
) {
    when {
        booksState.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        booksState.isSuccess != null -> {
            booksState.isSuccess.books?.let { books ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(2.dp)
                    ) {
                        items(books) { book ->
                            bookItem(
                                book,
                                navigationRoute,
                                onBookClicked = { onBookClicked(book.bookId) },
                                onAvatarClicked = { onAvatarClicked(book.email) }
                            )
                        }
                    }
                }
            }
        }


        booksState.error != null -> {
            Text(
                text = booksState.error,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun SelectedLocationBooks(
    booksState: BookStates,
    navigationRoute: String,
    onBookClicked: (bookId: Int?) -> Unit,
    onAvatarClicked: (email: String?) -> Unit
) {

    when {
        booksState.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        booksState.isSuccess != null -> {
            booksState.isSuccess.books?.let { books ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                ) {
                    LazyColumn(
                        contentPadding = PaddingValues(2.dp)
                    ) {
                        items(books) { book ->
                            bookItem(
                                book,
                                navigationRoute,
                                onBookClicked = { onBookClicked(book.bookId) },
                                onAvatarClicked = { onAvatarClicked(book.email) }
                            )
                        }
                    }
                }
            }
        }

        booksState.error != null -> {
            Text(
                text = booksState.error,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun BooksList(
    books: List<BookResponseItem>,
    navigationRoute: String,
    onBookClicked: (bookId: Int?) -> Unit,
    onAvatarClicked: (email: String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        LazyColumn(contentPadding = PaddingValues(2.dp)) {
            items(books) { bookItem ->
                bookItem(
                    bookItem,
                    navigationRoute,
                    onBookClicked = { id -> onBookClicked(bookItem.bookId) },
                    onAvatarClicked = { email -> onAvatarClicked(bookItem.email) }
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterCategoryChips(onCategorySelected: (String?) -> Unit) {
    val filters = listOf(
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

    var selectedFilter by remember { mutableStateOf<String?>(null) }

    LazyRow(userScrollEnabled = true, modifier = Modifier.padding(5.dp)) {
        items(filters) { filter ->
            FilterChip(
                onClick = {
                    selectedFilter = if (selectedFilter == filter) null else filter
                    onCategorySelected(if (filter == "All") null else selectedFilter)
                },
                label = { Text(filter) },
                modifier = Modifier.padding(3.dp),
                selected = (filter == selectedFilter),
                leadingIcon = {
                    if (filter == selectedFilter) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = Color.Red
                        )
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = Color.White
                )
            )
        }
    }
}

@Composable
fun FilterLocationChips(onLocationSelected: (String) -> Unit) {
    val locationFilters = listOf(
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
        "Kiambu",
        "Kilifi",
        "Kirinyaga",
        "Kisii",
        "Kisumu",
        "Kwale",
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
        "Turkana",
        "Uasin Gishu",
        "Vihiga",
        "Wajir",
        "West Pokot"
    )

    var selectedFilters by remember { mutableStateOf<String?>(null) }

    LazyRow(userScrollEnabled = true, modifier = Modifier.padding(5.dp)) {

        items(locationFilters) { locationFilters ->
            FilterChip(
                onClick = {
                    selectedFilters =
                        if (selectedFilters == locationFilters) null else locationFilters
                    (if (locationFilters == "All") null else selectedFilters)?.let { selectedFilters ->
                        onLocationSelected(
                            selectedFilters
                        )
                    }
                },
                label = { Text(locationFilters) },
                modifier = Modifier.padding(3.dp),
                selected = (locationFilters == selectedFilters),
                leadingIcon = {
                    if (locationFilters == selectedFilters) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Done icon",
                            modifier = Modifier.size(FilterChipDefaults.IconSize),
                            tint = Color.Red
                        )
                    } else {
                        null
                    }
                },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    labelColor = Color.White
                )
            )
        }

    }
}









