package com.example.movemate.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movemate.ui.calculate.CalculateScreen
import com.example.movemate.ui.home.HomeScreen
import com.example.movemate.ui.shipments.ShipmentsTabLayout
import com.example.movemate.ui.theme.DarkGray
import com.example.movemate.ui.theme.Purple

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(Screen.Home, Screen.Calculate, Screen.Shipment, Screen.Profile)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color.White) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(ImageVector.vectorResource(id = screen.icon), contentDescription = stringResource(id =screen.title)) },
                label = { Text(stringResource(id =screen.title)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Purple,
                    unselectedIconColor = DarkGray,
                    selectedTextColor = Purple,
                    unselectedTextColor = DarkGray,
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) { HomeScreen() }
        composable(Screen.Calculate.route) { CalculateScreen() }
        composable(Screen.Shipment.route) { ShipmentsTabLayout(navController::navigateUp) }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Profile Screen")
    }
}