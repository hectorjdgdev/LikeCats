package com.dsoles.mobile.getyourcats.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dsoles.mobile.getyourcats.common.data.BreedEntry
import com.dsoles.mobile.getyourcats.common.repository.SharedDataRepository


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BreedCardComponent(
    breed: BreedEntry,
    isFavorite: Boolean,
    onClickAddFavorite: () -> Unit,
    onClickARemoveFavorite: () -> Unit,
    onClickInCard: (breedId: String, screenParent: String) -> Unit = { _, _ -> },
    screenParent: String
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 10.dp, vertical = 10.dp),
        elevation = 4.dp,
        onClick = {
            SharedDataRepository.breedSelected =
                breed
            onClickInCard(breed.id, screenParent)
        }
    ) {
        Column {
            Box(modifier = Modifier.fillMaxHeight(fraction = 0.7f)) {
                val showShimmer = remember { mutableStateOf(true) }
                AsyncImage(
                    model = breed.breedImageUrl,
                    contentDescription = "Breed Image",
                    modifier = Modifier
                        .background(
                            shimmerBrush(
                                targetValue = 1300f,
                                showShimmer = showShimmer.value
                            )
                        )
                        .fillMaxSize(),
                    onSuccess = { showShimmer.value = false },
                    contentScale = ContentScale.Crop,

                )
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
            Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)) {
                Text(text = breed.name ?: "Breed Name", style = MaterialTheme.typography.h6)
            }
        }
    }
}
