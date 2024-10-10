package com.naveenprince.github.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.naveenprince.github.ui.search.SearchUsersScreen
import com.naveenprince.github.ui.users.UserDetailsScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

/**
 * Created by Naveen.
 */
@Composable
fun GitHubUsersNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = GitHubUsersNavRoute.USER_LIST.route,
    ) {
        composable(GitHubUsersNavRoute.USER_LIST.route) {
            SearchUsersScreen(onUserClick = { userUrl ->
                val encodedUrl = URLEncoder.encode(userUrl, StandardCharsets.UTF_8.toString())
                navController.navigate(GitHubUsersNavRoute.USER_DETAILS.route.plus("/$encodedUrl"))
            })
        }
        composable(GitHubUsersNavRoute.USER_DETAILS.route.plus("/{encodedUrl}")) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("encodedUrl")
            if (encodedUrl != null) {
                val userUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
                UserDetailsScreen(userUrl = userUrl, onBackClick = {
                    navController.popBackStack()
                })
            }
        }
    }
}