package com.dsoles.mobile.getyourcats.common.ui.components

import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dsoles.mobile.getyourcats.ui.navigation.BottomNavItem


@Composable
fun BottomBar(navController: NavController, items: List<BottomNavItem>) {
    val selectedItem by remember { mutableStateOf(items.first().screen_route) }
    Box {
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.title) },
                    selected = item.screen_route == selectedItem,
                    onClick = {
                        navController.navigate(item.screen_route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}