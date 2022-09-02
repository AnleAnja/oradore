package com.example.oradore.android

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
import androidx.lifecycle.viewmodel.compose.viewModel

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector, val index: Int) {
    object Program : BottomNavigationScreens("Programm", Icons.Rounded.List, 0)
    object Speakers : BottomNavigationScreens("Speaker", Icons.Rounded.People, 1)
    object Rooms : BottomNavigationScreens("Räume", Icons.Rounded.Room, 2)
    object Favorites : BottomNavigationScreens("Favoriten", Icons.Rounded.Grade, 3)
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
        }
        composable(BottomNavigationScreens.Speakers.route) {
        }
        composable(BottomNavigationScreens.Rooms.route) {
        }
        composable(BottomNavigationScreens.Favorites.route) {
        }
    }
}

@Composable
fun BottomBar(
    viewModel: AppViewModel
) {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavigationScreens.Program,
        BottomNavigationScreens.Speakers,
        BottomNavigationScreens.Rooms,
        BottomNavigationScreens.Favorites
    )
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val currentRoute = currentRoute(navController)
                navItems.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, "Icon") },
                        label = { Text(screen.route) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            viewModel.screen = screen.index
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