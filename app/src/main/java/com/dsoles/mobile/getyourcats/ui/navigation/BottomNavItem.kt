package com.dsoles.mobile.getyourcats.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.dsoles.mobile.getyourcats.modules.home.ui.screens.HomeScreen

sealed class BottomNavItem(var title: String, var icon: ImageVector, var screen_route: String) {
    object Home : BottomNavItem("Home", Icons.Filled.Home, HomeScreen.route)
    object Favorite : BottomNavItem("Favorite", Icons.Filled.Star, HomeScreen.route)
}