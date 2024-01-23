package com.dsoles.mobile.getyourcats.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.dsoles.mobile.getyourcats.ui.navigation.BottomNavItem


@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Favorite)
    var selectedItem by remember { mutableStateOf(items.first().screen_route) }
    Column {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.primary,
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = null,
                            tint = if (item.screen_route == selectedItem) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
                        )
                    },
                    label = { Text(item.title) },
                    selected = item.screen_route == selectedItem,
                    onClick = {
                        selectedItem = item.screen_route
                        navController.navigate(item.screen_route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .height(25.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}