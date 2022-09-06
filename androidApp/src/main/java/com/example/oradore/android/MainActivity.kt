package com.example.oradore.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OradoreTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    val viewModel: AppViewModel = viewModel()
    Scaffold(
        topBar = { TopBar(viewModel) }
    ) { padding -> // We need to pass scaffold's inner padding to content. That's why we use Box.
        Box(modifier = Modifier.padding(padding)) {
            Navigation(viewModel)
        }
    }
}

@Composable
fun TopBar(viewModel: AppViewModel) {
    when (viewModel.screen) {
        0 -> TopAppBar(
            title = { Text("Programm") },
            backgroundColor = MaterialTheme.colors.primary
        )
        1 -> TopAppBar(
            title = { Text("Speaker") },
            backgroundColor = MaterialTheme.colors.primary
        )
        2 -> TopAppBar(
            title = { Text("Räume") },
            backgroundColor = MaterialTheme.colors.primary
        )
        3 -> TopAppBar(
            title = { Text("Favoriten") },
            backgroundColor = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun Navigation(viewModel: AppViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, viewModel)
        }
        composable(
            "program/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            // TODO all of this can be removed as soon as we hit the actual http api in shared
            val programEntryId = backStackEntry.arguments?.getString("id") ?: return@composable
            val programEntry = viewModel.programEntryById(programEntryId) ?: return@composable
            val room = viewModel.roomById(programEntry.roomId) ?: return@composable
            val speakers = viewModel.speakerPreviewByProgramId(programEntry.id) ?: return@composable
            ProgramDetailView(programEntry, room, speakers, viewModel)
        }
        composable(
            "room/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            // TODO all of this can be removed as soon as we hit the actual http api in shared
            val roomId = backStackEntry.arguments?.getString("id") ?: return@composable
            val room = viewModel.roomById(roomId) ?: return@composable
            RoomDetailView(room = room)
        }
    }
}

@Composable
fun MainScreen(navController: NavController, viewModel: AppViewModel) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Column {
        SearchView(textState)
        BottomBar(viewModel) {
            composable(BottomNavigationScreens.Program.route) {
                viewModel.fetchProgramEntries()
                textState.value = TextFieldValue("") // reset search
                ProgramListView(viewModel.programEntries, textState) { programEntry ->
                    navigateToProgramDetailScreen(navController, programEntry.id)
                }
            }
            composable(BottomNavigationScreens.Speakers.route) {
                viewModel.fetchSpeakers()
                textState.value = TextFieldValue("")
                SpeakerListView(viewModel.speakers, textState) { speaker ->

                }
            }
            composable(BottomNavigationScreens.Rooms.route) {
                viewModel.fetchRooms()
                textState.value = TextFieldValue("") // reset search
                RoomListView(viewModel.rooms, textState) { room ->
                    navigateToRoomDetailScreen(navController, room.id)
                }
            }
            composable(BottomNavigationScreens.Favorites.route) {
                Text("D")
            }
        }
    }
}

fun navigateToProgramDetailScreen(
    navController: NavController,
    id: String
) {
    navController.navigate("program/${id}") {
        popUpTo("main") { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}

fun navigateToRoomDetailScreen(
    navController: NavController,
    id: String
) {
    navController.navigate("room/${id}") {
        popUpTo("main") { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}