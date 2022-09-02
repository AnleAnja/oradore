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

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector) {
    object Program : BottomNavigationScreens("Programm", Icons.Rounded.List)
    object Speakers : BottomNavigationScreens("Speaker", Icons.Rounded.People)
    object Rooms : BottomNavigationScreens("Räume", Icons.Rounded.Room)
    object Favorites : BottomNavigationScreens("Favoriten", Icons.Rounded.Grade)
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
fun Frame() {
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