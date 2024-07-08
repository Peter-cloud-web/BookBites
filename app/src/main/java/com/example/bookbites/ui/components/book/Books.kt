import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.bookbites.model.books.BookResponseItem
import com.example.bookbites.store.SessionManager
import com.example.bookbites.ui.components.NavigationDrawer
import com.example.bookbites.ui.components.book.BooksList
import com.example.bookbites.ui.components.book.appBars.BottomAppBar
import com.example.bookbites.ui.components.book.appBars.TopAppBar
import com.example.bookbites.ui.components.book.bookItem
import com.example.bookbites.ui.uistates.BookStates
import com.example.bookbites.ui.viewmodels.BooksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


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

    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(DrawerValue.Closed)

    var isDrawerOpen by remember { mutableStateOf(false) }

    if (isDrawerOpen) {
        NavigationDrawer(drawerState = drawerState)
    }



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
                },
                onOpenDrawer = {
                    isDrawerOpen
                }
            )
        },
        bottomBar = {
            BottomAppBar(navController)
        },
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
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}

suspend fun logoutUser(sessionManager: SessionManager) {
    withContext(Dispatchers.IO) {
        sessionManager.clearSession()
    }
}










