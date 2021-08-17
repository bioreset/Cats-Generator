package com.dariusz.catsgenerator.data.remote

import com.dariusz.catsgenerator.domain.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatsGeneratorAPI {

    @GET("cat/says/{text}")
    suspend fun getRandomCat(
        @Path("text") text: Text? = "",
        @Query("filter") filter: Filter? = "",
        @Query("tag") tag: Tag? = "",
        @Query("color") color: Color? = "",
        @Query("size") size: Size? = ""
    ): String

    @GET("api/tags")
    suspend fun getListOfTags(): List<Tag>

}