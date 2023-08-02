/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.data.GitHubRepository
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.presenter.MainActivity.Companion.lastSearchDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchRepositoriesViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository
) : ViewModel() {

    private val _repositoryItems = MutableLiveData<List<Item>>()
    val repositoryItems: LiveData<List<Item>> = _repositoryItems

    suspend fun searchResults(inputText: String) {
        viewModelScope.launch {
            val response = gitHubRepository.getRepositories(inputText)

            val repositories = withContext(Dispatchers.Default) {
                response.items.map { item ->
                    Item(
                        name = item.fullName,
                        ownerIconUrl = item.owner.avatarUrl,
                        language = item.language,
                        stargazersCount = item.stargazersCount,
                        watchersCount = item.watchersCount,
                        forksCount = item.forksCount,
                        openIssuesCount = item.openIssuesCount,
                    )
                }
            }

            _repositoryItems.value = repositories
            lastSearchDate = Date()
        }.join()
    }
}