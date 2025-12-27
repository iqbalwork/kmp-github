package com.iqbalwork.kmpgithub

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.iqbalwork.kmpgithub.data.model.UserResponse
import org.koin.compose.viewmodel.koinViewModel

/**
 * iqbalfauzi
 * Email: work.iqbalfauzi@gmail.com
 * Github: https://github.com/iqbalwork
 */
@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {

    val listUser by viewModel.listUsers.collectAsStateWithLifecycle()

    MainContent(
        listUser = listUser,
        username = viewModel.userName.value,
        onUsernameChanged = viewModel::onUserNameChanged
    ) { }
}

@Composable
fun MainContent(
    listUser: List<UserResponse>,
    username: String,
    onUsernameChanged: (String) -> Unit,
    onSearchUser: () -> Unit
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = username,
                onValueChange = onUsernameChanged,
                label = { Text("Type github username here!") }
            )
            Button(
                onClick = onSearchUser,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search")
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listUser.size) {
                    UserItem(user = listUser[it])
                }
            }
        }
    }
}

val dummyUserList = listOf(
    UserResponse(
        login = "iqbalwork",
        id = 719327,
        nodeId = "U_kgDOBQOQeg",
        avatarUrl = "https://avatars.githubusercontent.com/u/92508282?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/iqbalwork",
        htmlUrl = "https://github.com/iqbalwork",
        followersUrl = "https://api.github.com/users/iqbalwork/followers",
        followingUrl = "https://api.github.com/users/iqbalwork/following{/other_user}",
        gistsUrl = "https://api.github.com/users/iqbalwork/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/iqbalwork/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/iqbalwork/subscriptions",
        organizationsUrl = "https://api.github.com/users/iqbalwork/orgs",
        reposUrl = "https://api.github.com/users/iqbalwork/repos",
        eventsUrl = "https://api.github.com/users/iqbalwork/events{/privacy}",
        receivedEventsUrl = "https://api.github.com/users/iqbalwork/received_events",
        type = "User",
        userViewType = "public",
        siteAdmin = false,
        name = "Iqbal Fauzi",
        company = "@bobobox-id",
        blog = "",
        location = "Bandung",
        email = "work.iqbalfauzi@gmail.com",
        hireable = false,
        bio = "",
        twitterUsername = "",
        notificationEmail = "",
        publicRepos = 13,
        publicGists = 0,
        followers = 1,
        following = 3,
        createdAt = "2021-10-14T06:46:58Z",
        updatedAt = "2025-12-21T13:50:52Z"
    ),
    UserResponse(
        login = "johndoe",
        id = 1321,
        nodeId = "Yjjljdklaf",
        avatarUrl = "https://avatars.githubusercontent.com/u/jdlajflk?v=4",
        gravatarId = "",
        url = "https://api.github.com/users/iqbalwork",
        htmlUrl = "https://github.com/iqbalwork",
        followersUrl = "https://api.github.com/users/iqbalwork/followers",
        followingUrl = "https://api.github.com/users/iqbalwork/following{/other_user}",
        gistsUrl = "https://api.github.com/users/iqbalwork/gists{/gist_id}",
        starredUrl = "https://api.github.com/users/iqbalwork/starred{/owner}{/repo}",
        subscriptionsUrl = "https://api.github.com/users/iqbalwork/subscriptions",
        organizationsUrl = "https://api.github.com/users/iqbalwork/orgs",
        reposUrl = "https://api.github.com/users/iqbalwork/repos",
        eventsUrl = "https://api.github.com/users/iqbalwork/events{/privacy}",
        receivedEventsUrl = "https://api.github.com/users/iqbalwork/received_events",
        type = "User",
        userViewType = "public",
        siteAdmin = false,
        name = "John Doe",
        company = "@bobobox-id",
        blog = "",
        location = "Bandung",
        email = "johndoe@gmail.com",
        hireable = false,
        bio = "",
        twitterUsername = "",
        notificationEmail = "",
        publicRepos = 13,
        publicGists = 0,
        followers = 1,
        following = 3,
        createdAt = "2021-10-14T06:46:58Z",
        updatedAt = "2025-12-21T13:50:52Z"
    ),
)

@Composable
fun UserItem(modifier: Modifier = Modifier, user: UserResponse) {
    Row(
        modifier = modifier
            .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
            .padding(12.dp)
            .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            user.avatarUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
        )
        Text(text = user.login.orEmpty(), fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewUserItem() {
    UserItem(modifier = Modifier, dummyUserList.first())
}

@Preview
@Composable
private fun PreviewMainContent() {
    MainContent(
        onSearchUser = { },
        listUser = dummyUserList,
        username = "",
        onUsernameChanged = { }
    )
}
