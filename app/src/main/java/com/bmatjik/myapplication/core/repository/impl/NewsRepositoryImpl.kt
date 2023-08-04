package com.bmatjik.myapplication.core.repository.impl

import com.bmatjik.myapplication.core.remote.api.NewsApi
import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.core.toDomain
import com.bmatjik.myapplication.feature.model.NewsSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(private val newsApi: NewsApi) : NewsRepository {
    override suspend fun getNewsSourceByCategory(category: String): Result<List<NewsSource>> {
        return newsApi.getNewsSource(category).fold(onSuccess = { result ->
            val mappedData = result.sources?.map {
                it.toDomain()
            } ?: listOf()
            Result.success(mappedData)
        }, onFailure = {
            Result.failure(it)
        })
    }
}