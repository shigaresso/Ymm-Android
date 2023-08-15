package jp.co.yumemi.android.code_check.presenter.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.presenter.SearchRepositoriesViewModel
import jp.co.yumemi.android.code_check.presenter.ui.component.SearchBar


@Composable
fun SearchRepositoriesScreen(
    viewModel: SearchRepositoriesViewModel = hiltViewModel(),
    navigateToDetailScreen: (Item) -> Unit
) {
    val items by viewModel.repositoryItems.collectAsState()

    Column {
        SearchBar(searchCommand = { text: String -> viewModel.searchResults(text) })
        if (items.isNotEmpty()) {
            LazyColumn {
                items(items) { item ->
                    Box(modifier = Modifier
                        .padding(6.dp)
                        .clickable { navigateToDetailScreen(item) }) {
                        Text(item.name, fontSize = 12.sp)
                    }

                    Divider()
                }
            }
        }
    }
}