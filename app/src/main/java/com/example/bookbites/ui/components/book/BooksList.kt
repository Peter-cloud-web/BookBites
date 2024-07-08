package com.example.bookbites.ui.components.book

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookbites.model.books.BookResponseItem

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