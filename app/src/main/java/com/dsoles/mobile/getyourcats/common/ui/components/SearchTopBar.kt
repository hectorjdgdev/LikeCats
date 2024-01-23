package com.dsoles.mobile.getyourcats.common.ui.components


import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dsoles.mobile.getyourcats.R
import com.dsoles.mobile.getyourcats.common.viewmodel.SharedViewModel

@Composable
fun SearchTopBar(
    sharedViewModel: SharedViewModel,
    modifier: Modifier = Modifier,
    hint: String = "",
) {
    var searchText by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(top = 10.dp, bottom = 10.dp)
    ) {

        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary,
                    letterSpacing = 1.5.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    sharedViewModel.setSearchText(it)
                },
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search Icon")
                },
                placeholder = { Text(hint, color = MaterialTheme.colorScheme.onBackground) },
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colorScheme.background,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(30.dp)
            )
        }


    }

}