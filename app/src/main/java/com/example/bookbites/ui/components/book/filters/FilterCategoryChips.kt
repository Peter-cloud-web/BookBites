package com.example.bookbites.ui.components.book.filters

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.util.option_lists.categoryOptions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FilterCategoryChips(onCategorySelected: (String?) -> Unit) {
    var selectedFilter by remember { mutableStateOf<String?>(null) }

    LazyRow(userScrollEnabled = true, modifier = Modifier.padding(5.dp)) {
        items(categoryOptions) { filter ->
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


