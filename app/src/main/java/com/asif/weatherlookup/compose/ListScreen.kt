package com.asif.weatherlookup.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asif.weatherlookup.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ListScreen() {

    var searchQuery by remember { mutableStateOf("") }

    val list = currentItems.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            // Search bar
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = searchQuery,
                onValueChange = {
                    searchQuery = it
                    // Trigger filtering when the search query changes
                    filterItems(searchQuery)
                },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.Black
                    )
                },
                trailingIcon = {
                    // Clear the search query when the "clear" icon is clicked
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { searchQuery = "" }) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // Trigger any additional search action if needed
                    }
                ),
            )
            LazyColumn {
                items(list.value.toList()) {
                    Text(text = it.name, modifier = Modifier.height(100.dp))
                }
            }
        }
    }


}

@Preview
@Composable
fun ListPreview() {
    ListScreen()
}

suspend fun getList() {
    val list1 = (1..20).toMutableSet()
    data.emit(list1.toMutableSet())
    delay(5000)
    val list2 = (21..40).toMutableSet()
    data.emit((data.value + list2.toMutableSet()) as MutableSet<Int>)
    delay(5000)
    val list3 = (41..60).toMutableSet()
    data.emit((data.value + list3.toMutableSet()) as MutableSet<Int>)
}

var data: MutableStateFlow<MutableSet<Int>> = MutableStateFlow(mutableSetOf())
val currentData: MutableState<MutableStateFlow<MutableSet<Int>>> = mutableStateOf(data)

private var items: MutableStateFlow<MutableSet<Item>> = MutableStateFlow(
    mutableSetOf(
        Item("apple", 1),
        Item("banana", 2),
        Item("orange", 3),
        Item("grape", 4),
        Item("kiwi", 5)
    )
)
val currentItems: MutableStateFlow<Set<Item>> = MutableStateFlow(items.value)


fun filterItems(query: String) {
    if (query.isEmpty()) {
        // If the query is empty, show all items
        currentItems.value = items.value
    } else {
        // Filter items based on the query
        currentItems.value = items.value.filter { it.name.startsWith(query, true) }.toSet()
    }
}

data class Item(var name: String, var number: Int)


