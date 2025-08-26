package com.naveenprince.github.ui.navigation

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ResponsiveSearchAndDetailsScreen(
    navController: NavHostController,
    windowSizeClass: WindowSizeClass = calculateWindowSizeClass(LocalContext.current as Activity)
) {
    val selectedUserUrl = rememberSaveable { mutableStateOf<String?>(null) }
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> PhoneLayout(navController)
        else -> TabletLayout(selectedUserUrl)
    }
}

@Composable
private fun TabletLayout(selectedUserUrl: MutableState<String?>) {
    Row(modifier = Modifier.fillMaxSize()) {
        SearchUsersSection(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight(),
            onUserClick = { url -> selectedUserUrl.value = url }
        )

        UserDetailsSection(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight(),
            selectedUserUrl = selectedUserUrl
        )
    }
}

@Composable
private fun PhoneLayout(navController: NavHostController) {
    SearchUsersScreen(
        onUserClick = { userUrl ->
            val encodedUrl = URLEncoder.encode(userUrl, StandardCharsets.UTF_8.toString())
            navController.navigate(GitHubUsersNavRoute.USER_DETAILS.route + "/$encodedUrl")
        }
    )
}

@Composable
private fun SearchUsersSection(
    modifier: Modifier = Modifier,
    onUserClick: (String) -> Unit
) {
    Box(modifier = modifier) {
        SearchUsersScreen(onUserClick = onUserClick)
    }
}

@Composable
private fun UserDetailsSection(
    modifier: Modifier = Modifier,
    selectedUserUrl: MutableState<String?>
) {
    Box(modifier = modifier) {
        selectedUserUrl.value?.let { url ->
            UserDetailsScreen(
                userUrl = url,
                onBackClick = { selectedUserUrl.value = null }
            )
        } ?: EmptyDetailsPlaceholder()
    }
}

@Composable
private fun EmptyDetailsPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Select a user to see details")
    }
}

@Composable
fun GitHubUsersNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController, startDestination = GitHubUsersNavRoute.USER_LIST.route,
    ) {
        composable(GitHubUsersNavRoute.USER_LIST.route) {
            ResponsiveSearchAndDetailsScreen(navController = navController)
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
