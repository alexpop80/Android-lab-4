package com.example.lab_4.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.lab_4.R
import com.example.lab_4.model.Name
import com.example.lab_4.model.Picture
import com.example.lab_4.model.Urlinfo
import com.example.lab_4.ui.theme.theme.Lab_4Theme

@Composable
fun HomeScreen( //display what is need at the time based on ui state
    userUiState: UserUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    when (userUiState) {
        is UserUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is UserUiState.Success -> UserListScreen(
            userUiState.photos, contentPadding = contentPadding, modifier = modifier.fillMaxWidth()
        )
        is UserUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}

/**
 * The home screen displaying the loading message.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * this what the user see if the app load right
 */
@Composable
fun UserListScreen( //display users from list
    users: List<Urlinfo>?,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    if (users.isNullOrEmpty()) {
        // Show a message if users list is empty or null
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = modifier.padding(horizontal = 4.dp),
            contentPadding = contentPadding,
        ) {
            items(items = users ?: emptyList(), key = { photo -> photo.picture.large }) { photo ->
                UserInfoCard(
                    user = photo,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .aspectRatio(5.0f)
                )
            }
        }
    }
}

/**
 * if something goes this pop up
 */
@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

/**
 * The home screen displaying UserInfoCard
 */


@Composable
fun UserInfoCard(user: Urlinfo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

    ) {
        Box{
            Row {

            Image(
                painter = rememberImagePainter(data = user.picture.large),
                contentDescription = "Profile picture",
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),

                contentScale = ContentScale.Crop
            )
                Spacer(modifier = Modifier.width(16.dp))
          Column {
              Text("" + user.name.first + " " + user.name.last, fontWeight = FontWeight.Bold )
              Spacer(modifier = Modifier.width(16.dp))
              Text("" + user.email)
             }
          }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    Lab_4Theme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    Lab_4Theme {
        ErrorScreen({})
    }
}

