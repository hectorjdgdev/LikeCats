package com.dsoles.mobile.getyourcats

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContainer(
    sharedViewModel: SharedViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val listFavIds by favoriteViewModel.listFavDB.collectAsState()

    val onClickInCard = { breedId: String, screenParent: String ->
        navController.navigate(DetailScreen.route + "?${DetailScreen.PARAM_BREED_ID}=$breedId&${DetailScreen.PARAM_SCREEN_PARNT}=$screenParent") {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


    Scaffold(modifier = Modifier.fillMaxSize()){
        NavHost(navController, startDestination = MainScreen.route) {
            composable(MainScreen.route) {
                MainScreen(
                    onClickInCard = onClickInCard,
                    sharedViewModel = sharedViewModel,
                    favoriteViewModel = favoriteViewModel
                )
            }

            composable(
                DetailScreen.route + "?${DetailScreen.PARAM_BREED_ID}={breedId}&${DetailScreen.PARAM_SCREEN_PARNT}={screenParent}",
                arguments = listOf(navArgument(DetailScreen.PARAM_BREED_ID) {
                    defaultValue = ""
                }, navArgument("screenParent") {
                    defaultValue = HomeScreen.route
                })
            ) {
                val breedId = remember {
                    it.arguments?.getString(DetailScreen.PARAM_BREED_ID) ?: ""
                }
                val screenParent = remember {
                    it.arguments?.getString(DetailScreen.PARAM_SCREEN_PARNT) ?: ""
                }
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
}