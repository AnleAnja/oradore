package com.example.oradore.android

import DetailsScreen
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
import com.example.oradore.models.ProgramEntryPreview

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
            "details/{item}",
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let { item ->
                DetailsScreen(item = item)
            }
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
                    navigateToProgramEntryDetailScreen(navController, programEntry)
                }
            }
            composable(BottomNavigationScreens.Speakers.route) {
                Text("B")
            }
            composable(BottomNavigationScreens.Rooms.route) {
                Text("C")
            }
            composable(BottomNavigationScreens.Favorites.route) {
                Text("D")
            }
        }
    }
}

fun navigateToProgramEntryDetailScreen(
    navController: NavController,
    programEntry: ProgramEntryPreview
) {
    navController.navigate("details/${programEntry.name}") {
        popUpTo("main") { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}