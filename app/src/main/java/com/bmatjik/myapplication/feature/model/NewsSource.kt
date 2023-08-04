package com.bmatjik.myapplication.feature.model

import com.squareup.moshi.Json

data class NewsSource(
    val category: String,
    val country: String,
    val description: String,
    val id: String,
    val language: String,
    val name: String,
)