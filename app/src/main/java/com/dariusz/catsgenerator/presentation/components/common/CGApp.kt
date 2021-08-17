package com.dariusz.catsgenerator.presentation.components.common

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.dariusz.catsgenerator.presentation.components.theme.CatsGeneratorTheme
import com.dariusz.catsgenerator.presentation.screens.CatScreen

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun CGApp() {
    CatsGeneratorTheme {
        Scaffold(
            topBar = {
                TopBar()
            },
            content = {
                CatScreen()
            }
        )
    }
}