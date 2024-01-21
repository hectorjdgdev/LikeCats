package com.dsoles.mobile.getyourcats.modules.favorite.ui.screens


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import com.dsoles.mobile.getyourcats.common.ui.components.BreedCardComponent
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteEvent
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel


object FavoriteScreen {
    const val route = "FavoriteScreen"
}

@Composable
fun FavoriteScreen(
    eventFavorite: (FavoriteEvent) -> Unit,
    favoriteViewModel: FavoriteViewModel
) {

    val listOfFavorite by favoriteViewModel.listOfFavorite.collectAsState()

    SideEffect {
        favoriteViewModel.getFavorites()
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(listOfFavorite.size) { item ->


            val breedId = listOfFavorite[item].id
            val breedName = listOfFavorite[item].name
            val breedImageUrl = listOfFavorite[item].breedImageUrl ?: ""
            val isFavorite = true

            val onClickAddFavorite =
                {
                    eventFavorite(
                        FavoriteEvent.FavoriteAddClicked(
                            breedId, breedName, breedImageUrl
                        )
                    )
                }
            val onClickARemoveFavorite = {
                eventFavorite(FavoriteEvent.FavoriteRemoveClicked(breedId))
            }

            BreedCardComponent(
                listOfFavorite[item].id,
                listOfFavorite[item].name,
                listOfFavorite[item].breedImageUrl ?: "",
                isFavorite = isFavorite,
                onClickAddFavorite,
                onClickARemoveFavorite
            )
        }
    }

}