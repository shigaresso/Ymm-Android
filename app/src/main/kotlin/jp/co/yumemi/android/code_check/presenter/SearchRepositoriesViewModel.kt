/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.data.GitHubRepository
import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.data.Repositories
import jp.co.yumemi.android.code_check.di.DefaultDispatcher
import jp.co.yumemi.android.code_check.presenter.MainActivity.Companion.lastSearchDate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SearchRepositoriesViewModel @Inject constructor(
    private val gitHubRepository: GitHubRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _repositoryItems = MutableStateFlow(listOf<Item>())
    val repositoryItems = _repositoryItems.asStateFlow()

    fun searchResults(inputText: String) {
        viewModelScope.launch {
            val response = gitHubRepository.getRepositories(inputText)
            _repositoryItems.value = translateObject(response)
            lastSearchDate = Date()
        }
    }

    private suspend fun translateObject(repositories: Repositories): List<Item> {
        return withContext(defaultDispatcher) {
            repositories.items.map { item ->
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
    }
}