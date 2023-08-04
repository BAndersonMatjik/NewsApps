package com.bmatjik.myapplication.core.repository

import com.bmatjik.myapplication.feature.model.NewsSource

interface NewsRepository {
    suspend fun getNewsSourceByCategory(category:String):Result<List<NewsSource>>
}