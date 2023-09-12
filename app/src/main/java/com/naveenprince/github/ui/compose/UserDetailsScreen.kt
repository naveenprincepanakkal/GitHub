package com.naveenprince.github.ui.compose

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
import com.naveenprince.github.model.data.UserDetailsResponse
import com.naveenprince.github.ui.theme.GitHubUsersTheme
import com.naveenprince.github.ui.theme.margin_large
import com.naveenprince.github.ui.theme.margin_medium
import com.naveenprince.github.ui.theme.margin_small
import com.naveenprince.github.ui.theme.margin_xlarge
import com.naveenprince.github.utils.ResponseStatus
import com.naveenprince.github.viewmodel.UserDetailsViewModel

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
    val userDetailsViewState by viewModel.userDetailsState.collectAsState()
    UserDetailsScreen(userDetailsViewState)
}

@Composable
fun UserDetailsScreen(
    userDetailsViewState: ResponseStatus<UserDetailsResponse>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (userDetailsViewState) {
            is ResponseStatus.Success -> UserDetails(userDetailsViewState.data)
            is ResponseStatus.Error -> ErrorMessage(errorMsg = userDetailsViewState.message)
            is ResponseStatus.Loading -> CenteredCircularProgressIndicator()
            else -> {}
        }
    }
}

@Composable
fun UserDetails(userDetails: UserDetailsResponse?) {
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
                model = userDetails?.avatarUrl,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Description",
                placeholder = painterResource(id = R.drawable.loading)
            )
            Text(text = "${userDetails?.login}")
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
                text = stringResource(id = R.string.followers) + "\n${userDetails?.followers}"
            )
            Text(
                modifier = Modifier
                    .weight(1.0f)
                    .padding(margin_small),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.public_repos) + "\n${userDetails?.publicRepos}"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
    GitHubUsersTheme {
        UserDetails(userDetails = null)
    }
}