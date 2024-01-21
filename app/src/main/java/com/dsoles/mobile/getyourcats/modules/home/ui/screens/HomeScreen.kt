package com.dsoles.mobile.getyourcats.modules.home.ui.screens

import android.annotation.SuppressLint
import android.util.Log
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
import com.dsoles.mobile.getyourcats.modules.home.viewmodel.SharedSearchViewModel


object HomeScreen {
    const val route = "HomeScreen"
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(
    sharedSearchViewModel: SharedSearchViewModel,
    homeViewModel: HomeViewModel,
) {
    val listBreedState by homeViewModel.listBreed.collectAsState()
    val searchState by sharedSearchViewModel.searchText.collectAsState()
    val gridState = rememberLazyGridState()

    SideEffect {
        homeViewModel.fetchData(searchState)
    }

    LazyVerticalGrid(columns = GridCells.Fixed(2), state = gridState) {
        items(listBreedState.size) { item ->
            BreedCardComponent(listBreedState[item], false)
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
