package jp.co.yumemi.android.code_check.presenter.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.android.code_check.Item

@Composable
fun SearchedRepositories(
    items: List<Item>,
    navigateToDetailScreen: (Item) -> Unit
) {
    LazyColumn {
        items(items) { item ->
            RepositoryUI(navigateToDetailScreen, item)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchedRepositoriesPreview() {
    val items = List(15) {
        Item(
            name = "JetBrains/kotlin",
            ownerIconUrl = "",
            language = "Kotlin",
            stargazersCount = 45404,
            watchersCount = 45404,
            forksCount = 5604,
            openIssuesCount = 156
        )
    }
    SearchedRepositories(items = items, navigateToDetailScreen = {})
}