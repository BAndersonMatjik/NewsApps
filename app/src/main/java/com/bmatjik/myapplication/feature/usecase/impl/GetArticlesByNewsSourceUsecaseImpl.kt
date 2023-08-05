package com.bmatjik.myapplication.feature.usecase.impl

import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.usecase.GetArticlesByNewsSourceUsecase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetArticlesByNewsSourceUsecaseImpl @Inject constructor(private val newsRepository: NewsRepository,private val coroutineDispatcher: CoroutineDispatcher):GetArticlesByNewsSourceUsecase {
    override suspend fun invoke(newsSource: String): Result<List<Article>> {
        return withContext(coroutineDispatcher){
            newsRepository.getArticlesByNewsSource(newsSource).fold(onSuccess = {
                 Result.success(it)
            }, onFailure = {
                 Result.failure(it)
            })
        }
    }
}