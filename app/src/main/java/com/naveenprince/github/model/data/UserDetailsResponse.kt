package com.naveenprince.github.model.data


import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("bio")
    val bio: String,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("business_plus")
    val businessPlus: Boolean?,
    @SerializedName("collaborators")
    val collaborators: Int?,
    @SerializedName("company")
    val company: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("disk_usage")
    val diskUsage: Int?,
    @SerializedName("email")
    val email: String,
    @SerializedName("events_url")
    val eventsUrl: String,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("followers_url")
    val followersUrl: String,
    @SerializedName("following")
    val following: Int,
    @SerializedName("following_url")
    val followingUrl: String,
    @SerializedName("gists_url")
    val gistsUrl: String,
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @SerializedName("hireable")
    val hireable: Boolean,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ldap_dn")
    val ldapDn: String?,
    @SerializedName("location")
    val location: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @SerializedName("owned_private_repos")
    val ownedPrivateRepos: Int?,
    @SerializedName("plan")
    val plan: Plan?,
    @SerializedName("private_gists")
    val privateGists: Int?,
    @SerializedName("public_gists")
    val publicGists: Int,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @SerializedName("repos_url")
    val reposUrl: String,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("starred_url")
    val starredUrl: String,
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerializedName("suspended_at")
    val suspendedAt: String?,
    @SerializedName("total_private_repos")
    val totalPrivateRepos: Int?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("two_factor_authentication")
    val twoFactorAuthentication: Boolean?,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("url")
    val url: String
)

data class Plan(
    @SerializedName("collaborators")
    val collaborators: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("private_repos")
    val privateRepos: Int,
    @SerializedName("space")
    val space: Int
)

data class UserDetails(
    val avatarUrl: String,
    val followers: Int,
    val login: String,
    val publicRepos: Int,
) {
    constructor(userDetailsResponse: UserDetailsResponse) : this(
        userDetailsResponse.avatarUrl,
        userDetailsResponse.followers,
        userDetailsResponse.login,
        userDetailsResponse.publicRepos,
    )
}