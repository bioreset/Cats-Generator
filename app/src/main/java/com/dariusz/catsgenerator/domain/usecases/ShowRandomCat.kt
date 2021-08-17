package com.dariusz.catsgenerator.domain.usecases

import com.dariusz.catsgenerator.data.remote.CatsGeneratorApiService
import com.dariusz.catsgenerator.domain.model.*
import javax.inject.Inject

interface ShowRandomCat {

    suspend fun getRandomCat(
        text: Text?,
        filter: Filter?,
        tag: Tag?,
        color: Color?,
        size: Size?
    ): String

}

class ShowRandomCatImpl
@Inject constructor(
    private val catsGeneratorApiService: CatsGeneratorApiService
) : ShowRandomCat {

    override suspend fun getRandomCat(
        text: Text?,
        filter: Filter?,
        tag: Tag?,
        color: Color?,
        size: Size?
    ) = catsGeneratorApiService.fetchRandomCat(
        text = text,
        filter = filter,
        tag = tag,
        color = color,
        size = size
    )


}