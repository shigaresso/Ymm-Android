package jp.co.yumemi.android.code_check.data

interface GitHubRepository {
    suspend fun getRepositories(inputText: String): Repositories
}