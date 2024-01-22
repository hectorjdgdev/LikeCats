package com.dsoles.mobile.getyourcats.modules.home.ui.screens

import android.annotation.SuppressLint
import android.view.WindowInsets.Side
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import com.dsoles.mobile.getyourcats.common.ui.components.BreedCardComponent
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
    onClickInCard: (breedId: String, screenParent: String) -> Unit = {_,_->}
) {
    val listBreedState by homeViewModel.listBreed.collectAsState()
    val searchState by sharedViewModel.searchText.collectAsState()
    val gridState = rememberLazyGridState()


    LazyVerticalGrid(columns = GridCells.Fixed(2), state = gridState) {
        items(listBreedState.size) { item ->

            val breedId = listBreedState[item].id
            val breedName = listBreedState[item].name
            val breedImageUrl = listBreedState[item].image?.url ?: ""
            val origin = listBreedState[item].origin ?: ""
            val temperament = listBreedState[item].temperament ?: ""
            val description = listBreedState[item].description ?: ""
            val isFavorite = breedId in listFavoritesId
            val onClickAddFavorite =
                {
                    eventFavorite(
                        FavoriteEvent.FavoriteAddClicked(
                            breedId, breedName, breedImageUrl, origin, temperament, description
                        )
                    )
                }
            val onClickARemoveFavorite = {
                eventFavorite(FavoriteEvent.FavoriteRemoveClicked(breedId))
            }
            BreedCardComponent(
                breedId,
                breedName,
                breedImageUrl,
                origin,
                temperament,
                description,
                isFavorite,
                onClickAddFavorite = onClickAddFavorite,
                onClickARemoveFavorite = onClickARemoveFavorite,
                onClickInCard,
                HomeScreen.route
            )
        }
    }

    LaunchedEffect(key1 = searchState) {
        homeViewModel.fetchData(searchState)
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
