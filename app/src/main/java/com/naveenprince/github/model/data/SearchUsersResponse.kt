package com.naveenprince.github.model.data


import com.google.gson.annotations.SerializedName

data class SearchUsersResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val userList: List<UserResponse> = listOf(),
    @SerializedName("total_count")
    val totalCount: Int
)

data class UserResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers")
    val followers: Int?,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following")
    val following: Int?,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("hireable")
    val hireable: Boolean?,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String?,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("public_gists")
    val publicGists: Int?,
    @SerializedName("public_repos")
    val publicRepos: Int?,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("score")
    val score: Double,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("suspended_at")
    val suspendedAt: String?,
    @SerializedName("text_matches")
    val textMatches: List<TextMatches>? = listOf(),
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("url")
    val url: String
)

data class TextMatches(
    @SerializedName("fragment")
    val fragment: String?,
    @SerializedName("matches")
    val matches: List<Matches>? = listOf(),
    @SerializedName("object_type")
    val objectType: String?,
    @SerializedName("object_url")
    val objectUrl: String?,
    @SerializedName("property")
    val `property`: String?
)

data class Matches(
    @SerializedName("indices")
    val indices: List<Int?>?,
    @SerializedName("text")
    val text: String?
)

data class User(
    val avatarUrl: String,
    val login: String,
    val url: String,
) {
    constructor(userResponse: UserResponse) : this(
        userResponse.avatarUrl,
        userResponse.login,
        userResponse.url,
    )
}