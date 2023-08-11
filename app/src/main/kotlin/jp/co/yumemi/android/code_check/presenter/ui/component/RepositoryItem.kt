package jp.co.yumemi.android.code_check.presenter.ui.component

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun RepositoryItem(text: String) {
    Text(text, fontSize = 12.sp)
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    RepositoryItem("JetBrains/kotlin")
}