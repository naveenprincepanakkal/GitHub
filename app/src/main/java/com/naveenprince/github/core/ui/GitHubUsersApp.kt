package com.naveenprince.github.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.naveenprince.github.core.ui.navigation.GitHubUsersNavHost

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
