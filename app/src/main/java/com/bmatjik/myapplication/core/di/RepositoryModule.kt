package com.bmatjik.myapplication.core.di

import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.core.repository.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsNewsRepository(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository
}