package com.dariusz.catsgenerator.presentation.screens

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import coil.annotation.ExperimentalCoilApi
import com.dariusz.catsgenerator.di.UseCasesModule.provideGetAllTagsAction
import com.dariusz.catsgenerator.di.UseCasesModule.provideShowRandomCatAction
import com.dariusz.catsgenerator.domain.model.Colors.Companion.getColors
import com.dariusz.catsgenerator.domain.model.Filters.Companion.getFilters
import com.dariusz.catsgenerator.presentation.components.detail.CatScreenBuilder
import com.dariusz.catsgenerator.utils.ViewModelUtils.composeViewModel

@ExperimentalCoilApi
@ExperimentalComposeUiApi
@Composable
fun CatScreen() {

    val viewModel = composeViewModel {
        CatScreenViewModel(
            provideGetAllTagsAction(),
            provideShowRandomCatAction()
        )
    }

    val tags by remember(viewModel) {
        viewModel.tags
    }.collectAsState()

    val catUrl by remember(viewModel) {
        viewModel.catUrl
    }.collectAsState()

    CatScreenBuilder(
        listOfTags = tags,
        listOfColors = getColors(),
        listOfFilters = getFilters(),
        action = { text, filter, tag, color, size ->
            viewModel.getCat(text = text, filter = filter, tag = tag, color = color, size = size)
        },
        finalUrl = catUrl
    )

    LaunchedEffect(Unit) {
        viewModel.getTags()
    }

}