package com.naveenprince.github.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.naveenprince.github.ui.feature.search.SearchUsersScreen

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
            SearchUsersScreen()
        }
    }
}