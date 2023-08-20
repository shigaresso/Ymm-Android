package jp.co.yumemi.android.code_check.presenter.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.android.code_check.Item

@Composable
fun RepositoryUI(
    navigateToDetailScreen: (Item) -> Unit,
    item: Item
) {
    Box(modifier = Modifier
        .padding(6.dp)
        .clickable { navigateToDetailScreen(item) }) {
        Text(item.name, fontSize = 12.sp)
    }

    Divider()
}

@Preview(showBackground = true)
@Composable
private fun RepositoryUIPreview() {
    val item = Item(
        name = "JetBrains/kotlin",
        ownerIconUrl = "",
        language = "Kotlin",
        stargazersCount = 45404,
        watchersCount = 45404,
        forksCount = 5604,
        openIssuesCount = 156
    )
    RepositoryUI(navigateToDetailScreen = {}, item = item)
}