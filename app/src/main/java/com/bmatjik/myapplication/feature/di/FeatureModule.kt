package com.bmatjik.myapplication.feature.di

import com.bmatjik.myapplication.feature.usecase.GetArticlesByNewsSourceUsecase
import com.bmatjik.myapplication.feature.usecase.GetCategoryUsecase
import com.bmatjik.myapplication.feature.usecase.GetNewsSourcesUsecase
import com.bmatjik.myapplication.feature.usecase.impl.GetArticlesByNewsSourceFilterUsecase
import com.bmatjik.myapplication.feature.usecase.impl.GetArticlesByNewsSourceFilterUsecaseImpl
import com.bmatjik.myapplication.feature.usecase.impl.GetArticlesByNewsSourceUsecaseImpl
import com.bmatjik.myapplication.feature.usecase.impl.GetCategoryUsecaseImpl
import com.bmatjik.myapplication.feature.usecase.impl.GetNewsSourcesUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FeatureModule {
    @Provides
    fun provideGetCategoryUsecase():GetCategoryUsecase= GetCategoryUsecaseImpl()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeatureBindsModule {

    @Binds
    abstract fun bindsGetArticlesByNewsSourceUsecase(getArticlesByNewsSourceUsecaseImpl: GetArticlesByNewsSourceUsecaseImpl):GetArticlesByNewsSourceUsecase
    @Binds
    abstract fun bindsGetArticlesByNewsSourceFilterUsecase(getArticlesByNewsSourceFilterUsecaseImpl: GetArticlesByNewsSourceFilterUsecaseImpl): GetArticlesByNewsSourceFilterUsecase
    @Binds
    abstract fun bindsGetNewsSourcesUsecase(getNewsSourcesUsecaseImpl: GetNewsSourcesUsecaseImpl):GetNewsSourcesUsecase

}