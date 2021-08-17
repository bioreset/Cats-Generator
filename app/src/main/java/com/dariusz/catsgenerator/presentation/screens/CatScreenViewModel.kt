package com.dariusz.catsgenerator.presentation.screens

import androidx.lifecycle.ViewModel
import com.dariusz.catsgenerator.domain.model.*
import com.dariusz.catsgenerator.domain.usecases.GetAllTags
import com.dariusz.catsgenerator.domain.usecases.ShowRandomCat
import com.dariusz.catsgenerator.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CatScreenViewModel
@Inject constructor(
    private val getAllTags: GetAllTags,
    private val showRandomCat: ShowRandomCat
) : ViewModel() {

    private var _tags = MutableStateFlow(
        listOf<Tag>()
    )
    val tags: StateFlow<List<Tag>> =
        _tags

    private var _catUrl = MutableStateFlow(
        ""
    )
    val catUrl: StateFlow<String> =
        _catUrl

    fun getTags() = launchVMTask {
        _tags.value = getAllTags.getAllTags()
    }

    fun getCat(
        text: Text?,
        filter: Filter?,
        tag: Tag?,
        color: Color?,
        size: Size?
    ) = launchVMTask {
        _catUrl.value = showRandomCat.getRandomCat(
            text = text,
            filter = filter,
            tag = tag,
            color = color,
            size = size
        )
    }
}