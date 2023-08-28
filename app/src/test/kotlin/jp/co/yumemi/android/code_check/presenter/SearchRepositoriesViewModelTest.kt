@file:OptIn(ExperimentalCoroutinesApi::class)

package jp.co.yumemi.android.code_check.presenter

import jp.co.yumemi.android.code_check.Item
import jp.co.yumemi.android.code_check.data.GitHubRepository
import jp.co.yumemi.android.code_check.data.Owner
import jp.co.yumemi.android.code_check.data.Repositories
import jp.co.yumemi.android.code_check.data.RepositoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Test


val repositoryList = listOf(
    RepositoryItem(
        fullName = "JetBrains/kotlin",
        owner = Owner(avatarUrl = "https://avatars.githubusercontent.com/u/878437?v=4"),
        language = "Kotlin",
        stargazersCount = 45580,
        watchersCount = 45580,
        forksCount = 5628,
        openIssuesCount = 158,
    )
)

val repository = listOf(
    Item(
        name = "JetBrains/kotlin",
        ownerIconUrl = "https://avatars.githubusercontent.com/u/878437?v=4",
        language = "Kotlin",
        stargazersCount = 45580,
        watchersCount = 45580,
        forksCount = 5628,
        openIssuesCount = 158,
    )
)

class FakeRepository : GitHubRepository {
    override suspend fun getRepositories(inputText: String): Repositories {
        return Repositories(
            incompleteResults = false,
            items = repositoryList,
            totalCount = 1
        )
    }
}

class SearchRepositoriesViewModelTest {
    @Test
    fun `GitHub APIを用いてデータの取得に成功した場合`() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)

        try {
            val fakeRepository = FakeRepository()
            val viewModel = SearchRepositoriesViewModel(fakeRepository, testDispatcher)

            viewModel.searchResults("kotlin")

            assertEquals(repository, viewModel.repositoryItems.value)
        } finally {
            Dispatchers.resetMain()
        }
    }
}