package com.example.oradore.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Grade
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Room
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector) {
    object Program : BottomNavigationScreens("Program", Icons.Rounded.List)
    object Speakers : BottomNavigationScreens("Speakers", Icons.Rounded.People)
    object Rooms : BottomNavigationScreens("Rooms", Icons.Rounded.Room)
    object Favorites : BottomNavigationScreens("Favorites", Icons.Rounded.Grade)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Frame()
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun NavigationConfig(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationScreens.Program.route
    ) {
        composable(BottomNavigationScreens.Program.route) {
            Text("Programm Liste")
        }
        composable(BottomNavigationScreens.Speakers.route) {
            Text("Speaker Liste")
        }
        composable(BottomNavigationScreens.Rooms.route) {
            Text("Raum Liste")
        }
        composable(BottomNavigationScreens.Favorites.route) {
            Text("Favoriten Liste")
        }
    }
}

@Composable
fun Frame() {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavigationScreens.Program,
        BottomNavigationScreens.Speakers,
        BottomNavigationScreens.Rooms,
        BottomNavigationScreens.Favorites
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Titel") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        bottomBar = {
            BottomNavigation {
                val currentRoute = currentRoute(navController)
                navItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, "Icon") },
                        label = { Text(screen.route) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route)
                            }
                        }
                    )
                }
            }
        }
    ) { _ ->
        NavigationConfig(navController)
    }
}