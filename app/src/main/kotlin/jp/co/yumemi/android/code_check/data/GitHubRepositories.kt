package jp.co.yumemi.android.code_check.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repositories(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    @SerialName("items")
    val items: List<RepositoryItem>,
    @SerialName("total_count")
    val totalCount: Int
)

@Serializable
data class RepositoryItem(
    @SerialName("full_name")
    val fullName: String,
    @SerialName("owner")
    val owner: Owner,
    @SerialName("language")
    val language: String = "",
    @SerialName("stargazers_count")
    val stargazersCount: Long,
    @SerialName("watchers_count")
    val watchersCount: Long,
    @SerialName("forks_count")
    val forksCount: Long,
    @SerialName("open_issues_count")
    val openIssuesCount: Long,
)

@Serializable
data class Owner(
    @SerialName("avatar_url")
    val avatarUrl: String,
)