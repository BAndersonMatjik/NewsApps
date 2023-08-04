package com.bmatjik.myapplication.core.repository.impl

import com.bmatjik.myapplication.core.remote.api.NewsApi
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.core.toDomain
import com.bmatjik.myapplication.feature.model.NewsSource
import com.squareup.moshi.Moshi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi,private val moshi: Moshi) : NewsRepository {
    override suspend fun getNewsSourceByCategory(category: String): Result<List<NewsSource>> {
        newsApi.getNewsSource(category).let { it ->
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
}