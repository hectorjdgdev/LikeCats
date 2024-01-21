package com.dsoles.mobile.getyourcats

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dsoles.mobile.getyourcats.common.ui.components.BottomBar
import com.dsoles.mobile.getyourcats.common.ui.components.SearchTopBar
import com.dsoles.mobile.getyourcats.modules.home.ui.screens.HomeScreen
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.SharedSearchViewModel
import com.dsoles.mobile.getyourcats.ui.navigation.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedSearchViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()

    val items = listOf(BottomNavItem.Home, BottomNavItem.Favorite)
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SearchTopBar(sharedViewModel)
        },
        bottomBar = {
            BottomBar(navController, items)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavigationGraph(
                navController,
                sharedViewModel,
                homeViewModel,
            )
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    sharedSearchViewModel: SharedSearchViewModel,
    homeViewModel: HomeViewModel
) {
    NavHost(navController, startDestination = HomeScreen.route) {
        composable(HomeScreen.route) {
            HomeScreen(sharedSearchViewModel, homeViewModel)
        }
    }
}
