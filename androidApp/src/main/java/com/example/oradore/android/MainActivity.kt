package com.example.oradore.android

import android.os.Bundle
import android.util.Log
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
            "${DetailScreen.Program.label}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            // TODO all of this can be removed as soon as we hit the actual http api in shared
            val programId = backStackEntry.arguments?.getString("id") ?: return@composable
            val programEntry = viewModel.programEntryById(programId) ?: return@composable
            val room = viewModel.roomById(programEntry.roomId) ?: return@composable
            val speakers = viewModel.speakerPreviewByProgramId(programEntry.id) ?: return@composable
            ProgramDetailView(programEntry, room, speakers, viewModel)
        }
        composable(
            "${DetailScreen.Speaker.label}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val speakerId = backStackEntry.arguments?.getString("id")
        }
        composable(
            "${DetailScreen.Room.label}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("id")
        }
        composable(
            "${DetailScreen.Favorite.label}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val favId = backStackEntry.arguments?.getString("id")
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
                    navigateToDetailScreen(navController, DetailScreen.Program, programEntry.id)
                }
            }
            composable(BottomNavigationScreens.Speakers.route) {
                viewModel.fetchSpeakers()
                textState.value = TextFieldValue("")
                SpeakerListView(viewModel.speakers, textState) { speaker ->
                    navigateToDetailScreen(navController, DetailScreen.Speaker, speaker.id)
                }
            }
            composable(BottomNavigationScreens.Rooms.route) {
                viewModel.fetchRooms()
                textState.value = TextFieldValue("") // reset search
                RoomListView(viewModel.rooms, textState) { room ->
                    navigateToDetailScreen(navController, DetailScreen.Room, room.id)
                }
            }
            composable(BottomNavigationScreens.Favorites.route) {
                Text("D")
            }
        }
    }
}

fun navigateToDetailScreen(
    navController: NavController,
    detailScreen: DetailScreen,
    id: String
) {
    navController.navigate("${detailScreen.label}/${id}") {
        popUpTo("main") { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}