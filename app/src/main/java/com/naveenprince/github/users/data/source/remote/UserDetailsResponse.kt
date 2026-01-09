package com.naveenprince.github.users.data.source.remote

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserDetailsResponse(
    @SerialName("avatar_url")
    val avatarUrl: String,
    @SerialName("bio")
    val bio: String? = null,
    @SerialName("blog")
    val blog: String? = null,
    @SerialName("business_plus")
    val businessPlus: Boolean? = null,
    @SerialName("collaborators")
    val collaborators: Int? = null,
    @SerialName("company")
    val company: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("disk_usage")
    val diskUsage: Int? = null,
    @SerialName("email")
    val email: String? = null,
    @SerialName("events_url")
    val eventsUrl: String,
    @SerialName("followers")
    val followers: Int,
    @SerialName("followers_url")
    val followersUrl: String,
    @SerialName("following")
    val following: Int,
    @SerialName("following_url")
    val followingUrl: String,
    @SerialName("gists_url")
    val gistsUrl: String,
    @SerialName("gravatar_id")
    val gravatarId: String? = null,
    @SerialName("hireable")
    val hireable: Boolean? = null,
    @SerialName("html_url")
    val htmlUrl: String,
    @SerialName("id")
    val id: Int,
    @SerialName("ldap_dn")
    val ldapDn: String? = null,
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
    @SerialName("owned_private_repos")
    val ownedPrivateRepos: Int? = null,
    @SerialName("plan")
    val plan: Plan? = null,
    @SerialName("private_gists")
    val privateGists: Int? = null,
    @SerialName("public_gists")
    val publicGists: Int,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("received_events_url")
    val receivedEventsUrl: String,
    @SerialName("repos_url")
    val reposUrl: String,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String,
    @SerialName("suspended_at")
    val suspendedAt: String? = null,
    @SerialName("total_private_repos")
    val totalPrivateRepos: Int? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("two_factor_authentication")
    val twoFactorAuthentication: Boolean? = null,
    @SerialName("type")
    val type: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("url")
    val url: String
)

@Serializable
data class Plan(
    @SerialName("collaborators")
    val collaborators: Int,
    @SerialName("name")
    val name: String,
    @SerialName("private_repos")
    val privateRepos: Int,
    @SerialName("space")
    val space: Int
)
