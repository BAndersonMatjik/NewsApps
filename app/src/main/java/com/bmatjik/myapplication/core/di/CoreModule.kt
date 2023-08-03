package com.bmatjik.myapplication.core.di

import com.bmatjik.myapplication.BuildConfig
import com.bmatjik.myapplication.MainActivity
import com.bmatjik.myapplication.core.remote.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreModule {

    @Provides
    @NewsUrl
    @Singleton
    fun providesNewsApiUrl(): String = BuildConfig.NEWS_API_URL

    @Provides
    @NewsKey
    @Singleton
    fun providesNewsApiKey(): String = BuildConfig.NEWS_API_KEY

    @Provides
    @Singleton
    fun providesRetrofit(@NewsUrl url: String): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }


    //handle qualifier for DI
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NewsUrl

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class NewsKey
}