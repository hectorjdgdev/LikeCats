package com.dsoles.mobile.getyourcats.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RetryComponent(error: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = error, color = Color.Red, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = { onRetry() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Retry")
            }
        }

    }
}