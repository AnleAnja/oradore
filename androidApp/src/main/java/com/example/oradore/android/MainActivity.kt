package com.example.oradore.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector) {
    object Program : BottomNavigationScreens("Programm", Icons.Rounded.List)
    object Speakers : BottomNavigationScreens("Speaker", Icons.Rounded.People)
    object Rooms : BottomNavigationScreens("Räume", Icons.Rounded.Room)
    object Favorites : BottomNavigationScreens("Favoriten", Icons.Rounded.Grade)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember { mutableStateOf(0) }
            Scaffold(
                topBar = { TopBar(screen)}
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

@Composable
fun DetailsScreen(item: String) {
    Text(text = "Detailseite ${item}")
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
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape // The TextFiled has rounded corners top left and right by default
    )
}

@Composable
fun ListItem(text: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(text) })
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun List(navController: NavController, state: MutableState<TextFieldValue>) {
    val items: ArrayList<String> = arrayListOf("Anja", "Alex", "CK", "Dominik", "Döner", "Sandy")
    var filteredItems: ArrayList<String>
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredItems = if (searchedText.isEmpty()) {
            items
        } else {
            val resultList = ArrayList<String>()
            for (item in items) {
                if (item.lowercase().contains(searchedText.lowercase())) {
                    resultList.add(item)
                }
            }
            resultList
        }
        items(filteredItems) { filteredItem ->
            ListItem(
                text = filteredItem,
                onItemClick = { selectedItem ->
                    navController.navigate("details/$selectedItem") {
                        popUpTo("main") {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
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
    ) {_ ->
        NavigationConfig(navController)
    }
}

