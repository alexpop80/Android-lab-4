package com.example.lab_4.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab_4.ui.theme.screens.HomeScreen
import com.example.lab_4.ui.theme.screens.UserViewModel
import com.example.lab_4.R

// this get it ready for main activity
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoapp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { UserTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            val userViewModel: UserViewModel =
                viewModel(factory = UserViewModel.Factory)
            HomeScreen(
                userUiState = userViewModel.userUiState,
                retryAction = userViewModel::getUserInfo,
                contentPadding = it
            )
        }
    }
}

//this display the top bar on
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}
