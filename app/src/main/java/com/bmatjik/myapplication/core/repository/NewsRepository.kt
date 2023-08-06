package com.bmatjik.myapplication.core.repository

import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.model.NewsSource

interface NewsRepository {
    suspend fun getNewsSourcesByCategory(category:String):Result<List<NewsSource>>

    suspend fun getArticlesByNewsSource(newsSource: String,pageSize:String,page:String,search:String?):Result<List<Article>>

}