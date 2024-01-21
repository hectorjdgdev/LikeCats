package com.dsoles.mobile.getyourcats

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dsoles.mobile.getyourcats.common.ui.components.BottomBar
import com.dsoles.mobile.getyourcats.common.ui.components.SearchTopBar
import com.dsoles.mobile.getyourcats.modules.favorite.ui.screens.FavoriteScreen
import com.dsoles.mobile.getyourcats.modules.home.ui.screens.HomeScreen
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteEvent
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.ui.navigation.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    val listFavId by favoriteViewModel.listFavDB.collectAsState()
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
                favoriteViewModel,
                listFavId
            ) { event ->
                favoriteViewModel.onEvent(event)
            }
        }
    }
}

@Composable
fun NavigationGraph(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    homeViewModel: HomeViewModel,
    favoriteViewModel: FavoriteViewModel,
    listFavId: Set<String>,
    eventFavorite: (FavoriteEvent) -> Unit
) {
    NavHost(navController, startDestination = HomeScreen.route) {
        composable(HomeScreen.route) {
            HomeScreen(sharedViewModel, homeViewModel,listFavId, eventFavorite = eventFavorite)
        }

        composable(FavoriteScreen.route) {
            FavoriteScreen(eventFavorite = eventFavorite, favoriteViewModel)
        }
    }
}
