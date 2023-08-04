package com.bmatjik.myapplication.core.remote.model

 data class NewsSourcesResponse(
    val status: String,
    val code:String?="",
    val message:String?="",
    val sources:List<NewsSourceResponse>?=null
)

