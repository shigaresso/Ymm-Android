package jp.co.yumemi.android.code_check.presenter.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import jp.co.yumemi.android.code_check.Item

@Composable
fun RepositoryItems(items: List<Item>) {
    LazyColumn {
        items(items) { item ->
            RepositoryItem(text = item.name)
            Divider()
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun RepositoryItemsPreview() {
    val items = listOf(
        Item("JetBrains/kotlin", "", "", 0, 0, 0, 0),
        Item("TheAlgorithms/Kotlin", "", "", 0, 0, 0, 0),
        Item("Kotlin/kotlinx.coroutines", "", "", 0, 0, 0, 0),
    )
    RepositoryItems(items)
}