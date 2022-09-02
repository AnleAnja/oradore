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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
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
            var screen by remember { mutableStateOf(0) }
            Scaffold(
                topBar = { TopBar(screen) }
            ) { padding -> // We need to pass scaffold's inner padding to content. That's why we use Box.
                Box(modifier = Modifier.padding(padding)) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun TopBar(screen: Int) {
    when (screen) {
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
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController = navController)
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
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        List(navController = navController, state = textState)
        Frame()
    }
}