package com.bmatjik.myapplication.core.remote.api

import com.bmatjik.myapplication.core.remote.model.BaseResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/sources")
    suspend fun getNewsSource(@Query("category") category:String):Result<NewsSourcesResponse>
}