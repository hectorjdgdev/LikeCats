package com.dsoles.mobile.getyourcats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.detail.ui.screens.DetailScreen
import com.dsoles.mobile.getyourcats.modules.favorite.ui.screens.FavoriteScreen
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.modules.home.ui.screens.HomeScreen

@Composable
fun MainContainer(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val listFavIds by favoriteViewModel.listFavDB.collectAsState()

    val onClickInCard = { breedId: String, screenParent: String ->
        navController.navigate(DetailScreen.route + "?breedId=$breedId&screenParent=$screenParent") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }



    NavHost(navController, startDestination = MainScreen.route) {
        composable(MainScreen.route) {
            MainScreen(
                onClickInCard = onClickInCard,
                sharedViewModel = sharedViewModel,
                favoriteViewModel = favoriteViewModel
            )
        }

        composable(
            DetailScreen.route + "?breedId={breedId}&screenParent={screenParent}",
            arguments = listOf(navArgument("breedId") {
                defaultValue = ""
            }, navArgument("screenParent") {
                defaultValue = HomeScreen.route
            })
        ) {
            val breedId = it.arguments?.getString("breedId") ?: ""
            val screenParent = it.arguments?.getString("screenParent") ?: ""
            DetailScreen(
                onClickBack = {
                    navController.navigateUp()
                },
                breedId,
                isFavorite = screenParent == FavoriteScreen.route || breedId in listFavIds,
                eventFavorite = { event ->
                    favoriteViewModel.onEvent(event)
                })
        }
    }
}