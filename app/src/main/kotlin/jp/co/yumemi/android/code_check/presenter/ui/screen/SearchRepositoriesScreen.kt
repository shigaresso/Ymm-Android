package jp.co.yumemi.android.code_check.presenter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import jp.co.yumemi.android.code_check.presenter.SearchRepositoriesViewModel
import jp.co.yumemi.android.code_check.presenter.ui.component.RepositoryItems
import jp.co.yumemi.android.code_check.presenter.ui.component.SearchBar


@Composable
fun SearchRepositoriesScreen(
    viewModel: SearchRepositoriesViewModel = viewModel()
) {
    val items by viewModel.repositoryItems.collectAsState()

    Column {
        SearchBar(searchCommand = { text: String -> viewModel.searchResults(text) })
        if (items.isNotEmpty()) {
            RepositoryItems(items = items)
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchRepositoriesScreenPreview() {
    SearchRepositoriesScreen()
}