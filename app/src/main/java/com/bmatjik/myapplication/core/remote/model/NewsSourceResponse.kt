package com.bmatjik.myapplication.core.remote.model

import com.squareup.moshi.Json
 data class NewsSourceResponse(
    @Json(name = "category")
    val category: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "language")
    val language: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)