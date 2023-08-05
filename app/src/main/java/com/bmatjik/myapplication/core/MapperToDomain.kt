package com.bmatjik.myapplication.core

import com.bmatjik.myapplication.core.remote.model.ArticleResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourceResponse
import com.bmatjik.myapplication.feature.model.Article
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


fun ArticleResponse.toDomain():Article{
    return Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = source.name,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}