package com.bmatjik.myapplication.core.repository.impl

import com.bmatjik.myapplication.core.remote.api.NewsApi
import com.bmatjik.myapplication.core.remote.model.ArticlesResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.core.toDomain
import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.model.NewsSource
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi,private val moshi: Moshi) : NewsRepository {
    override suspend fun getNewsSourcesByCategory(category: String): Result<List<NewsSource>> {
        newsApi.getNewsSources(category).let { it ->
            if (it.code() != 200) {
                return Result.failure(Exception(moshi.adapter(NewsSourcesResponse::class.java).fromJson(it.errorBody()?.string()?:"")?.message))
            } else {
                if (it.body()?.status == "error") {
                    return Result.failure(Exception(it.body()?.message))
                } else {
                    val sources = it.body()?.sources ?: listOf()
                    val mappedSources = sources.map { source ->
                        source.toDomain()
                    }
                    return Result.success(mappedSources)
                }
            }
        }
    }

    override suspend fun getArticlesByNewsSource(newsSource: String,pageSize:String,page:String,search:String?): Result<List<Article>> {
        newsApi.getArticlesByNewsSource(newsSource,pageSize, page,search).let {response ->
            if (response.code()!=200){
                return Result.failure(Exception(moshi.adapter(ArticlesResponse::class.java).fromJson(response.errorBody()?.string()?:"")?.message))
            }else{
                if (response.body()?.status == "error"){
                    return Result.failure(Exception(response.body()?.message))
                }else{
                    val articles = response.body()?.articles ?: listOf()
                    val mappedSources = articles.map { article ->
                        article.toDomain()
                    }
                    return Result.success(mappedSources)
                }
            }
        }
    }
}