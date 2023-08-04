package com.bmatjik.myapplication.core.remote.model

import com.squareup.moshi.Json

data class NewsSourcesResponse(
     @Json(name = "status")
    val status: String,
     @Json(name = "code")
    val code:String?="",
     @Json(name = "message")
    val message:String?="",
     @Json(name = "sources")
    val sources:List<NewsSourceResponse>?=null
)

