package com.bmatjik.myapplication.core.remote.model

import com.squareup.moshi.Json

data class SourceResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String
)