package com.naveenprince.github.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.naveenprince.github.R
import com.naveenprince.github.ui.theme.margin_xlarge

/**
 * Created by Naveen.
 */

@Composable
fun CenteredCircularProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(modifier = Modifier.testTag("LOADING"))
    }
}

@Composable
fun ErrorMessage(errorMsg: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(margin_xlarge)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stringResource(id = R.string.error) + ": $errorMsg",
            color = MaterialTheme.colorScheme.error
        )
    }
}