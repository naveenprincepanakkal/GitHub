package com.naveenprince.github.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.naveenprince.github.ui.compose.NavigationRoute
import com.naveenprince.github.ui.compose.UserScreen

/**
 * Class to determine navigation paths
 *
 * Created by Naveen.
 */

@Composable
fun GitHubUsersApp() {
    val navController = rememberNavController()
    GitHubUsersNavHost(
        navController = navController
    )
}

@Composable
fun GitHubUsersNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = NavigationRoute.USER_LIST.route,
    ) {
        composable(NavigationRoute.USER_LIST.route) {
            UserScreen()
        }
    }
}