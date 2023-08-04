package com.bmatjik.myapplication.feature.di

import com.bmatjik.myapplication.feature.usecase.GetCategoryUseCase
import com.bmatjik.myapplication.feature.usecase.GetNewsSourcesUsecase
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
    fun provideGetCategoryUsecase():GetCategoryUseCase= GetCategoryUsecaseImpl()
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class FeatureBindsModule {

    @Binds
    abstract fun bindsGetNewsSourcesUsecase(getNewsSourcesUsecaseImpl: GetNewsSourcesUsecaseImpl):GetNewsSourcesUsecase

}