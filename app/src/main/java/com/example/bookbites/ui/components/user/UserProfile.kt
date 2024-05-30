package com.example.bookbites.ui.components.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bookbites.model.user_details.AssociatedBook
import com.example.bookbites.ui.viewmodels.UserProfileViewModel

@Composable
fun UserProfile(email: String) {
    val userProfileViewModel: UserProfileViewModel = hiltViewModel()
    val userProfile = userProfileViewModel.userProfile.collectAsStateWithLifecycle().value

    Column {

        when {
            userProfile.isLoading -> {
                CircularProgressIndicator()
            }

            userProfile.isSuccess != null -> {

                userProfile.isSuccess.userDetailsResponseItem.map { userDetailsResponseItem ->
                    userDetailsResponseItem.associatedBooks.let { associatedBooksList ->
                        UserDetailsItem()
                        UserAssociatedBooks(associatedBooks  = associatedBooksList)
                    }
                }
            }

            userProfile.isError != null -> "An unexpected error occurred"

        }
    }
}

@Composable
fun UserAssociatedBooks(associatedBooks: List<AssociatedBook>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(associatedBooks) { associatedBookItem ->
                AssociatedBooksItem(associatedBookItem)
            }
        }
    }

}