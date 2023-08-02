package jp.co.yumemi.android.code_check

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.http.HttpHeaders
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class GitHubRepositoryImpl(
    private val httpClient: HttpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
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