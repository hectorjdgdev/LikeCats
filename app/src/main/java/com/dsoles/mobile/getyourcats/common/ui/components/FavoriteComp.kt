package com.dsoles.mobile.getyourcats.common.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FavoriteStartComponent(
    onClickAddFavorite: () -> Unit,
    onClickARemoveFavorite: () -> Unit,
    isFavorite: Boolean = false,
) {
    var checked by remember { mutableStateOf(isFavorite) }
    Box(
        modifier = Modifier
            .width(36.dp)
            .height(36.dp)
            .toggleable(value = checked, onValueChange = {
                checked = it
                if (checked) {
                    onClickAddFavorite()
                } else {
                    onClickARemoveFavorite()
                }
            })
    ) {
        if (checked) Icon(
            Icons.Rounded.Star,
            tint = Color.Yellow,
            contentDescription = "Start",
            modifier = Modifier.fillMaxSize()
        )
        else Icon(
            Icons.TwoTone.Star,
            tint = Color.White,
            contentDescription = "Start",
            modifier = Modifier.fillMaxSize()
        )
    }
}