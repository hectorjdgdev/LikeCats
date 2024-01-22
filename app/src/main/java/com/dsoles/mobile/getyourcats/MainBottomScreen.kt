package com.dsoles.mobile.getyourcats

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dsoles.mobile.getyourcats.common.ui.components.BottomBar
import com.dsoles.mobile.getyourcats.common.ui.components.SearchTopBar
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.detail.ui.screens.DetailScreen
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel
import com.dsoles.mobile.getyourcats.ui.navigation.NavigatorScreenContainer


object MainScreen{
    val route = "MainScreen"
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel,
    favoriteViewModel: FavoriteViewModel,
    onClickInCard: (breedId: String,screenParent: String) -> Unit
) {

    val listFavId by favoriteViewModel.listFavDB.collectAsState()
    val navController = rememberNavController()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            SearchTopBar(sharedViewModel)
        },
        bottomBar = {
            BottomBar(navController)

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavigatorScreenContainer(
                navController,
                sharedViewModel,
                homeViewModel,
                favoriteViewModel,
                listFavId,
                { event ->
                    favoriteViewModel.onEvent(event)
                },
                onClickInCard
            )
        }
    }
}
