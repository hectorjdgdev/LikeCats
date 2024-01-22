package com.dsoles.mobile.getyourcats.modules.home.ui.screens

import android.annotation.SuppressLint
import android.view.WindowInsets.Side
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.common.ui.components.BreedCardComponent
import com.dsoles.mobile.getyourcats.common.ui.components.RetryComponent
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteEvent


object HomeScreen {
    const val route = "HomeScreen"
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    sharedViewModel: SharedViewModel,
    homeViewModel: HomeViewModel,
    listFavoritesId: Set<String>,
    eventFavorite: (FavoriteEvent) -> Unit,
    onClickInCard: (breedId: String, screenParent: String) -> Unit = { _, _ -> }
) {
    val listBreedState by homeViewModel.listBreed.collectAsState()
    val searchState by sharedViewModel.searchText.collectAsState()
    val gridState = rememberLazyGridState()

    val loadErrorState by remember { homeViewModel.loadErrorState }
    val loadingState by remember { homeViewModel.isLoadingState }


    Box() {
        if (loadingState) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        } else if (loadErrorState.isNotEmpty()) {
            RetryComponent(loadErrorState) {
                homeViewModel.activeIsLoadingGeneral()
                homeViewModel.fetchData(searchState)
            }
        }
    }

    LaunchedEffect(key1 = searchState) {
        homeViewModel.fetchData(searchState)
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2), state = gridState) {
        items(listBreedState.size) { item ->

            val breedId = listBreedState[item].id
            val breedName = listBreedState[item].name
            val breedImageUrl = listBreedState[item].breedImageUrl ?: ""
            val origin = listBreedState[item].origin ?: ""
            val temperament = listBreedState[item].temperament ?: ""
            val description = listBreedState[item].description ?: ""
            val isFavorite = breedId in listFavoritesId

            val breed =
                BreedEntry(breedId, breedName, breedImageUrl, origin, temperament, description)

            val onClickAddFavorite =
                {
                    eventFavorite(
                        FavoriteEvent.FavoriteAddClicked(
                            breed
                        )
                    )
                }
            val onClickARemoveFavorite = {
                eventFavorite(FavoriteEvent.FavoriteRemoveClicked(breed))
            }

            BreedCardComponent(
                breed,
                isFavorite,
                onClickAddFavorite = onClickAddFavorite,
                onClickARemoveFavorite = onClickARemoveFavorite,
                onClickInCard,
                HomeScreen.route
            )
        }
    }



    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItem = visibleItems.lastOrNull() ?: return@collect
                if (lastVisibleItem.index >= listBreedState.size - 1) {
                    homeViewModel.fetchData(searchState)
                }
            }
    }
}

