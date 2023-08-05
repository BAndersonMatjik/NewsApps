package com.bmatjik.myapplication.core.remote.api

import com.bmatjik.myapplication.core.remote.model.ArticlesResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/sources")
    suspend fun getNewsSources(@Query("category") category:String): Response<NewsSourcesResponse>

    @GET("v2/everything?sources=wired")
    suspend fun getArticlesByNewsSource(@Query("sources") sources:String):Response<ArticlesResponse>
}