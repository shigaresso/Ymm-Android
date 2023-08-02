/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.co.yumemi.android.code_check.MainActivity.Companion.lastSearchDate
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
            val newRepositories = mutableListOf<Item>()

            withContext(Dispatchers.Default) {
                response.items.map { item ->
                    newRepositories.add(
                        Item(
                            name = item.fullName,
                            ownerIconUrl = item.owner.avatarUrl,
                            language = item.language,
                            stargazersCount = item.stargazersCount,
                            watchersCount = item.watchersCount,
                            forksCount = item.forksCount,
                            openIssuesCount = item.openIssuesCount,
                        )
                    )
                }
            }

            _repositoryItems.value = newRepositories.toList()
            lastSearchDate = Date()
        }.join()
    }
}