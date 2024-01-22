package com.dsoles.mobile.getyourcats.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dsoles.mobile.getyourcats.common.ui.components.BottomBar
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.detail.ui.screens.DetailScreen
import com.dsoles.mobile.getyourcats.modules.favorite.ui.screens.FavoriteScreen
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteEvent
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.modules.home.ui.screens.HomeScreen
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel



@Composable
fun NavigatorScreenContainer(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    homeViewModel: HomeViewModel,
    favoriteViewModel: FavoriteViewModel,
    listFavId: Set<String>,
    eventFavorite: (FavoriteEvent) -> Unit,
    onClickInCard: (breedId: String, screenParent: String) -> Unit
) {
    NavHost(navController, startDestination = HomeScreen.route) {

        composable(HomeScreen.route) {
            HomeScreen(
                sharedViewModel,
                homeViewModel,
                listFavId,
                eventFavorite = eventFavorite,
                onClickInCard
            )
        }

        composable(FavoriteScreen.route) {
            FavoriteScreen(eventFavorite = eventFavorite, favoriteViewModel, onClickInCard)
        }
    }
}