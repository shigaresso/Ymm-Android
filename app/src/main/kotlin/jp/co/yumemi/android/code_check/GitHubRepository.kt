package jp.co.yumemi.android.code_check

interface GitHubRepository {
    suspend fun getRepositories(inputText: String): Repositories
}