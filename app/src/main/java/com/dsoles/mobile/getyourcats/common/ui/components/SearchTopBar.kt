package com.dsoles.mobile.getyourcats.common.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel

@Composable
fun SearchTopBar(
    sharedViewModel: SharedViewModel
) {
    var searchText by remember { mutableStateOf("") }
    Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    sharedViewModel.setSearchText(it)
                },
                placeholder = { Text("Search", color = Color.White) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .clip(RoundedCornerShape(30.dp)) // Rounded corners
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    textColor = Color.White,
                )
            )
        }
    }

}