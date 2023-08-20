package jp.co.yumemi.android.code_check.presenter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.presenter.SearchRepositoriesViewModel
import jp.co.yumemi.android.code_check.presenter.ui.component.SearchBar
import jp.co.yumemi.android.code_check.presenter.ui.component.SearchedRepositories

@Composable
fun SearchRepositoriesScreen(
    viewModel: SearchRepositoriesViewModel = hiltViewModel(),
    navigateToDetailScreen: (Item) -> Unit
) {
    val items by viewModel.repositoryItems.collectAsState()

    Column {
        SearchBar(searchCommand = { text: String -> viewModel.searchResults(text) })
        if (items.isNotEmpty()) {
            SearchedRepositories(items, navigateToDetailScreen)
        }
    }
}