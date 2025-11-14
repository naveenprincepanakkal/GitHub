package com.naveenprince.github.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import com.naveenprince.github.R
import com.naveenprince.github.domain.users.model.UserDetails
import com.naveenprince.github.ui.compose.CenteredCircularProgressIndicator
import com.naveenprince.github.ui.compose.ErrorMessage
import com.naveenprince.github.ui.theme.GitHubUsersTheme
import com.naveenprince.github.ui.theme.margin_large
import com.naveenprince.github.ui.theme.margin_medium
import com.naveenprince.github.ui.theme.margin_small
import com.naveenprince.github.ui.theme.margin_xlarge

/**
 * Created by Naveen.
 */

@Composable
fun UserDetailsScreen(
    userUrl: String,
    onBackClick: () -> Unit,
    viewModel: UserDetailsViewModel = hiltViewModel<UserDetailsViewModel>(),
) {
    val fetchedForUrl = rememberSaveable { mutableStateOf("") }
    LaunchedEffect(userUrl) {
        if (fetchedForUrl.value != userUrl) {
            viewModel.onIntent(UserDetailsIntent.FetchUserDetails(userUrl))
            fetchedForUrl.value = userUrl
        }
    }
    val userDetailsState by viewModel.userDetailsState.collectAsState()
    UserDetailsScreen(userDetailsState, onBackClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(
    userDetailsState: UserDetailsState,
    onBackClick: () -> Unit,
) {
    Column {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Navigate back")
                }
            },
            windowInsets = WindowInsets(0) // <-- removes the extra space //
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            if (userDetailsState.isLoading) {
                CenteredCircularProgressIndicator()
            } else {
                if (userDetailsState.error != null) {
                    ErrorMessage(errorMsg = userDetailsState.error)
                } else if (userDetailsState.userDetails == null) {
                    Text(text = stringResource(R.string.user_not_found))
                } else {
                    UserDetails(userDetailsState.userDetails)
                }
            }
        }
    }
}

@Composable
fun UserDetails(userDetails: UserDetails) {
    Card(
        modifier = Modifier
            .padding(margin_xlarge)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = margin_medium),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(margin_large),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = userDetails.avatarUrl,
                alignment = Alignment.Center,
                contentDescription = "User Image",
                placeholder = painterResource(id = R.drawable.loading)
            )
            Text(text = userDetails.login)
        }

        Row(
            modifier = Modifier
                .padding(margin_large)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(margin_small),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.followers) + "\n${userDetails.followers}"
            )
            Text(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(margin_small),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.public_repos) + "\n${userDetails.publicRepos}"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
    GitHubUsersTheme {
        //UserDetails(UserDetailsState())
    }
}