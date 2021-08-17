package com.dariusz.catsgenerator.data.remote

import com.dariusz.catsgenerator.di.NetworkModule.provideRetrofit
import com.dariusz.catsgenerator.domain.model.*
import com.dariusz.catsgenerator.utils.UrlBuilder.getRandomCatUrl
import retrofit2.HttpException
import javax.inject.Inject

interface CatsGeneratorApiService {

    suspend fun fetchRandomCat(
        text: Text?,
        filter: Filter?,
        tag: Tag?,
        color: Color?,
        size: Size?
    ): String

    suspend fun getListOfTags(): List<Tag>

}

class CatsGeneratorApiServiceImpl
@Inject constructor() :
    CatsGeneratorApiService {

    private val retrofit = provideRetrofit()

    override suspend fun fetchRandomCat(
        text: Text?,
        filter: Filter?,
        tag: Tag?,
        color: Color?,
        size: Size?
    ) = try {
        val stringPulled = retrofit.getRandomCat(
            text = text,
            filter = filter,
            tag = tag,
            color = color,
            size = size
        )
        if (stringPulled != "") {
            getRandomCatUrl(text = text, filter = filter, tag = tag, color = color, size = size)
        } else {
            ""
        }
    } catch (throwable: Throwable) {
        if (throwable is HttpException && throwable.code() == 404) "" else ""
    }

    override suspend fun getListOfTags() = retrofit.getListOfTags()

}