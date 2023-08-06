package com.bmatjik.myapplication.core.remote.model
import com.squareup.moshi.Json


data class ArticlesResponse(
    @Json(name = "articles")
    val articles: List<ArticleResponse>? = listOf(),
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int?,
    @Json(name = "code")
    val code:String?="",
    @Json(name = "message")
    val message:String?="",
)

