package com.dsoles.mobile.getyourcats.modules.detail.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dsoles.mobile.getyourcats.common.ui.components.FavoriteStartComponent
import com.dsoles.mobile.getyourcats.common.ui.components.SearchTopBar
import com.dsoles.mobile.getyourcats.modules.detail.ui.components.ParallaxComponent
import com.dsoles.mobile.getyourcats.modules.detail.viewmodel.DetailViewModel
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteEvent
import com.dsoles.mobile.getyourcats.modules.favorite.viewmodel.FavoriteViewModel
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.HomeViewModel


object DetailScreen {
    const val route = "DetailScreen"
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    onClickBack: () -> Unit,
    id: String,
    detailViewModel: DetailViewModel = hiltViewModel(),
    eventFavorite: (FavoriteEvent) -> Unit,
    isFavorite: Boolean = false
) {

    val favoriteObj by detailViewModel.favorite.collectAsState()

    LaunchedEffect(key1 = id) {
        detailViewModel.getFavorite(id, isFavorite)
    }

    val onClickAddFavorite =
        {
            favoriteObj?.let {
                eventFavorite(
                    FavoriteEvent.FavoriteAddClicked(
                        it
                    )
                )
            }
        }
    val onClickARemoveFavorite = {
        favoriteObj?.let {
            eventFavorite(FavoriteEvent.FavoriteRemoveClicked(it))

        }

    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Box(modifier = Modifier.padding(it)) {
            favoriteObj?.let { favorite ->
                ParallaxComponent(
                    id,
                    favorite.name ?: "Name",
                    favorite.breedImageUrl ?: "ImageUrl",
                    favorite.origin ?: "Origin",
                    favorite.temperament ?: "Temperament",
                    favorite.description ?: "Description",
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .height(100.dp)
                    .align(
                        Alignment.TopCenter
                    )
            ) {
                IconButton(onClick = { onClickBack() }) {
                    Icon(Icons.Filled.ArrowBack, tint = Color.Black, contentDescription = "Back")
                }
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
                    FavoriteStartComponent(
                        onClickAddFavorite = {
                            onClickAddFavorite()
                        },
                        onClickARemoveFavorite = {
                            onClickARemoveFavorite()
                        },
                        isFavorite = isFavorite
                    )
                }
            }
        }
    }
}