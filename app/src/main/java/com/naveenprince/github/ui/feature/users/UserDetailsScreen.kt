package com.naveenprince.github.ui.feature.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.naveenprince.github.R
import com.naveenprince.github.domain.model.UserDetails
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
    viewModel: UserDetailsViewModel = hiltViewModel(),
) {
    LaunchedEffect(true) {
        viewModel.fetchUserDetails(userUrl)
    }
    val userDetailsState by viewModel.userDetailsState.collectAsState(initial = UserDetailsState())
    UserDetailsScreen(userDetailsState)
}

@Composable
fun UserDetailsScreen(
    userDetailsState: UserDetailsState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
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
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Description",
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