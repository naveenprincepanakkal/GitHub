package com.naveenprince.github.ui.compose

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
import com.naveenprince.github.model.data.User
import com.naveenprince.github.ui.theme.GitHubUsersTheme
import com.naveenprince.github.ui.theme.margin_large
import com.naveenprince.github.ui.theme.margin_medium
import com.naveenprince.github.utils.ResponseStatus
import com.naveenprince.github.viewmodel.SearchUsersViewModel

/**
 * Created by Naveen.
 */

@Composable
fun UserScreen(
    viewModel: SearchUsersViewModel = hiltViewModel(),
) {
    val userListState by viewModel.userListState.collectAsState()
    val focusManager = LocalFocusManager.current
    UserScreen(
        userListState,
        onSearchClick = { query ->
            if (query.isNotEmpty()) {
                viewModel.searchUser(query)
                focusManager.clearFocus()
            }
        })
}

@Composable
private fun UserScreen(
    userListState: ResponseStatus<List<User>>,
    onSearchClick: (String) -> Unit
) {
    Column {
        SearchBar(onSearchClick = onSearchClick)
        when (userListState) {
            is ResponseStatus.Success -> UserListView(userListState.data ?: emptyList())
            is ResponseStatus.Error -> ErrorMessage(errorMsg = userListState.message)
            is ResponseStatus.Loading -> CenteredCircularProgressIndicator()
            else -> {}
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