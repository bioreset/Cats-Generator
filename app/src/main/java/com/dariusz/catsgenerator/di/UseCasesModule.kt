package com.dariusz.catsgenerator.di

import com.dariusz.catsgenerator.data.remote.CatsGeneratorApiServiceImpl
import com.dariusz.catsgenerator.domain.usecases.GetAllTags
import com.dariusz.catsgenerator.domain.usecases.GetAllTagsImpl
import com.dariusz.catsgenerator.domain.usecases.ShowRandomCat
import com.dariusz.catsgenerator.domain.usecases.ShowRandomCatImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideGetAllTagsAction(): GetAllTags = GetAllTagsImpl(
        CatsGeneratorApiServiceImpl()
    )

    @Provides
    fun provideShowRandomCatAction(): ShowRandomCat = ShowRandomCatImpl(
        CatsGeneratorApiServiceImpl()
    )
}