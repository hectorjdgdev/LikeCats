package com.dsoles.mobile.getyourcats.modules.detail.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dsoles.mobile.getyourcats.R
import com.dsoles.mobile.getyourcats.common.ui.components.FavoriteStartComponent
import com.dsoles.mobile.getyourcats.common.ui.components.shimmerBrush
import java.lang.Math.min


@Composable
fun ParallaxComponent(
    id: String,
    name: String,
    breedImageUrl: String,
    origin: String,
    temperament: String,
    description: String,
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                    translationY = 0.5f * scrollState.value
                },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight(fraction = 0.7f)
                    .fillMaxWidth()
            ) {
                Card(
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    ),
                    elevation = 20.dp,
                ) {
                    val showShimmer = remember { mutableStateOf(true) }
                    AsyncImage(
                        model = breedImageUrl,
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
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        Column {
            Text(
                text = name,
                style = TextStyle(
                    fontSize = 30.sp,
                    color = Color.Black,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            )
            Text(
                text = origin,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            )
            Text(
                text = temperament,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            )

            Text(
                text = description,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    letterSpacing = 1.5.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
            )
        }
    }

}