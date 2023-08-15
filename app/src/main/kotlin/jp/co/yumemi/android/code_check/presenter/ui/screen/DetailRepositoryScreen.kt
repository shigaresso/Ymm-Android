package jp.co.yumemi.android.code_check.presenter.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.R

@Composable
fun RepositoryScreen(
    item: Item
) {
    Column {
        if (item.ownerIconUrl.isNotEmpty()) {
            AsyncImage(
                model = item.ownerIconUrl,
                contentDescription = item.name
            )
            Divider()
        }
        Text(item.name)
        Divider()
        Text(stringResource(R.string.written_language, item.language))
        Divider()
        Text(stringResource(R.string.stars_view, item.stargazersCount))
        Divider()
        Text(stringResource(R.string.watchers_view, item.watchersCount))
        Divider()
        Text(stringResource(R.string.forks_view, item.forksCount))
        Divider()
        Text(stringResource(R.string.open_issues_view, item.openIssuesCount))
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryScreenPreview() {
    // Preview でネットから画像を表示しようとすると、他の UI も表示されないので URL は "" にして Preview している
    val item = Item(
        name = "JetBrains/kotlin",
        ownerIconUrl = "",
        language = "Kotlin",
        stargazersCount = 45404,
        watchersCount = 45404,
        forksCount = 5604,
        openIssuesCount = 156
    )
    RepositoryScreen(item)
}