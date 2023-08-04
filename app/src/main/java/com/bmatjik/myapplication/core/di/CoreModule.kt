package com.bmatjik.myapplication.core.di

import com.bmatjik.myapplication.BuildConfig
import com.bmatjik.myapplication.core.remote.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun providesRetrofit(@NewsUrl url: String,okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpLogging(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        }
    }

    @Provides
    @Singleton
    fun providesHttpSecretKeyInterceptor(@NewsKey newsKey: String): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder().addHeader("x-api-key", newsKey).build()
            val response = it.proceed(request)
            response
        }
    }


    @Provides
    @Singleton
    fun providesOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        secretKeyInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .addInterceptor(secretKeyInterceptor).build()
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