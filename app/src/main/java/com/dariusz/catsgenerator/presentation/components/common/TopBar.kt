package com.dariusz.catsgenerator.presentation.components.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Cat Generator") },
        backgroundColor = Color.Black,
        contentColor = Color.White
    )
}