package com.dariusz.catsgenerator.domain.usecases

import com.dariusz.catsgenerator.data.remote.CatsGeneratorApiService
import com.dariusz.catsgenerator.domain.model.Tag
import com.dariusz.catsgenerator.domain.model.Tags.Companion.getTags
import javax.inject.Inject

interface GetAllTags {

    suspend fun getAllTags(): List<Tag>

}

class GetAllTagsImpl
@Inject constructor(
    private val catsGeneratorApiService: CatsGeneratorApiService
) : GetAllTags {

    override suspend fun getAllTags() = getTags(catsGeneratorApiService.getListOfTags())

}