package com.bmatjik.myapplication.core

import com.bmatjik.myapplication.core.remote.model.NewsSourceResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import com.bmatjik.myapplication.feature.model.NewsSource

fun NewsSourceResponse.toDomain(): NewsSource {
    return NewsSource(
        category = category,
        country = country,
        description = description,
        id = id,
        language = language,
        name = name
    )
}