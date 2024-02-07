package com.naveenprince.github.ui.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.naveenprince.github.R
import com.naveenprince.github.domain.model.User
import com.naveenprince.github.ui.compose.CenteredCircularProgressIndicator
import com.naveenprince.github.ui.compose.CenteredText
import com.naveenprince.github.ui.compose.ErrorMessage
import com.naveenprince.github.ui.feature.users.UserDetailsScreen
import com.naveenprince.github.ui.theme.GitHubUsersTheme
import com.naveenprince.github.ui.theme.margin_large
import com.naveenprince.github.ui.theme.margin_medium

/**
 * Created by Naveen.
 */

@Composable
fun SearchUsersScreen(
    viewModel: SearchUsersViewModel = hiltViewModel(),
) {
    val searchUserState by viewModel.searchUsersState.collectAsState()
    val focusManager = LocalFocusManager.current
    SearchUsersScreen(
        searchUserState,
        onSearchClick = { query ->
            if (query.isNotEmpty()) {
                viewModel.searchUser(query)
                focusManager.clearFocus()
            }
        })
}

@Composable
private fun SearchUsersScreen(
    searchUserState: SearchUsersState,
    onSearchClick: (String) -> Unit
) {
    Column {
        SearchBar(onSearchClick = onSearchClick)
        if (searchUserState.isLoading) {
            CenteredCircularProgressIndicator()
        } else {
            if (searchUserState.error != null) {
                ErrorMessage(errorMsg = searchUserState.error)
            } else if (searchUserState.userList.isNullOrEmpty()) {
                if (searchUserState.userList != null)
                    CenteredText(text = stringResource(R.string.user_not_found))
            } else {
                UserListView(searchUserState.userList)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListView(userList: List<User>) {

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var userUrl by rememberSaveable { mutableStateOf("") }

    Column {
        LazyColumn {
            items(userList) { user ->
                UserCard(user, onUserClick = {
                    userUrl = it.url
                    showBottomSheet = true
                })
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                UserDetailsScreen(userUrl)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserCard(
    user: User,
    onUserClick: (User) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(margin_large)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(defaultElevation = margin_medium),
        onClick = { onUserClick(user) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            AsyncImage(
                modifier = Modifier.weight(1.0f),
                model = user.avatarUrl,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = "Description",
                placeholder = painterResource(id = R.drawable.loading)
            )

            Text(
                modifier = Modifier
                    .padding(margin_medium)
                    .weight(2.0f),
                text = user.login
            )
        }
    }
}

@Composable
fun SearchBar(
    onSearchClick: (String) -> Unit,
) {

    var queryString by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(margin_large),
        value = queryString,
        onValueChange = {
            queryString = it
        },
        trailingIcon = {
            IconButton(onClick = {
                onSearchClick(queryString)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                )
            }
        },
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            onSearchClick(queryString)
        }),
        singleLine = true,
    )
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    GitHubUsersTheme {
        SearchBar(onSearchClick = { })
        //UserCard(null, onUserClick = { })
    }
}