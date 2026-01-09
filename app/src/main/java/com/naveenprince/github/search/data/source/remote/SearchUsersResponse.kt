package com.naveenprince.github.search.data.source.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SearchUsersResponse(
    @SerialName("incomplete_results")
    val incompleteResults: Boolean,
    @SerialName("items")
    val userList: List<UserResponse> = listOf(),
    @SerialName("total_count")
    val totalCount: Int
)

@Serializable
data class UserResponse(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("blog")
    val blog: String? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("followers")
    val followers: Int? = null,
    @SerialName("followers_url")
    val followersUrl: String,
    @SerialName("following")
    val following: Int? = null,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("gists_url")
    val gistsUrl: String,
    @SerialName("gravatar_id")
    val gravatarId: String,
    @SerialName("hireable")
    val hireable: Boolean? = null,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("location")
    val location: String? = null,
    @SerialName("login")
    val login: String,
    @SerialName("name")
    val name: String? = null,
    @SerialName("node_id")
    val nodeId: String,
    @SerialName("organizations_url")
    val organizationsUrl: String,
    @SerialName("public_gists")
    val publicGists: Int? = null,
    @SerialName("public_repos")
    val publicRepos: Int? = null,
    @SerialName("received_events_url")
    val receivedEventsUrl: String,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("score")
    val score: Double,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerialName("suspended_at")
    val suspendedAt: String? = null,
    @SerialName("text_matches")
    val textMatches: List<TextMatches>? = listOf(),
    @SerialName("type")
    val type: String,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("url")
    val url: String
)

@Serializable
data class TextMatches(
    @SerialName("fragment")
    val fragment: String? = null,
    @SerialName("matches")
    val matches: List<Matches>? = listOf(),
    @SerialName("object_type")
    val objectType: String? = null,
    @SerialName("object_url")
    val objectUrl: String? = null,
    @SerialName("property")
    val `property`: String? = null
)

@Serializable
data class Matches(
    @SerialName("indices")
    val indices: List<Int?>? = null,
    @SerialName("text")
    val text: String? = null
)
