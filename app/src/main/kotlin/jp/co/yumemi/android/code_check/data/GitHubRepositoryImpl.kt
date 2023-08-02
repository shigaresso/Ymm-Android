package jp.co.yumemi.android.code_check.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient
) : GitHubRepository {
    override suspend fun getRepositories(inputText: String): Repositories {
        return withContext(Dispatchers.IO) {
            httpClient.get {
                url(GitHubApiUrl.SEARCH_REPOSITORIES)
                parameter("q", inputText)
                headers {
                    append(HttpHeaders.Accept, "application/vnd.github.v3+json")
                }
            }
        }
    }
}