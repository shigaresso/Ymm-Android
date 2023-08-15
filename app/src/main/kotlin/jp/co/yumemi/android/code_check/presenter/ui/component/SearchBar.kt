package jp.co.yumemi.android.code_check.presenter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchCommand: (String) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    androidx.compose.material3.SearchBar(
        query = searchText,
        onQueryChange = { searchText = it },
        onSearch = {
            searchCommand(searchText)
            active = false
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier.fillMaxWidth()
    ) {
    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(searchCommand = {})
}