package com.dsoles.mobile.getyourcats.modules.favorite.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.dsoles.mobile.getyourcats.common.data.BreedEntry

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
    favoriteViewModel: FavoriteViewModel,
    onClickInCard: (breedId: String, screenParent: String) -> Unit = { _, _ -> }
) {

    val listOfFavorite by favoriteViewModel.listOfFavorite.collectAsState()
    val averageLifeSpan by favoriteViewModel.averageLifeSpan.collectAsState()

    SideEffect {
        favoriteViewModel.getFavorites()
    }

    LaunchedEffect(key1 = listOfFavorite){
        favoriteViewModel.averageLifeSpanQuery()
    }

    Column {
        if(averageLifeSpan > 0){
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)) {
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.Center),
                    text = AnnotatedString("Avergage $averageLifeSpan")
                )
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(listOfFavorite.size) { item ->


                val breedId = listOfFavorite[item].id
                val breedName = listOfFavorite[item].name
                val breedImageUrl = listOfFavorite[item].breedImageUrl ?: ""
                val origin = listOfFavorite[item].origin
                val temperament = listOfFavorite[item].temperament
                val description = listOfFavorite[item].description
                val lifeSpan = listOfFavorite[item].lifeSpan
                val isFavorite = true

                val breed = BreedEntry(
                    breedId,
                    breedName,
                    breedImageUrl,
                    origin,
                    temperament,
                    description,
                    lifeSpan
                )

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
                    isFavorite = isFavorite,
                    onClickAddFavorite,
                    onClickARemoveFavorite,
                    onClickInCard,
                    FavoriteScreen.route
                )
            }
        }
    }


}