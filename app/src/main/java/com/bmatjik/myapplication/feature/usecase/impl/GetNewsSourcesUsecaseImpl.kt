package com.bmatjik.myapplication.feature.usecase.impl

import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.feature.model.NewsSource
import com.bmatjik.myapplication.feature.usecase.GetNewsSourcesUsecase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GetNewsSourcesUsecaseImpl @Inject constructor(private val newsSourceRepository: NewsRepository,private val coroutineDispatcher: CoroutineDispatcher):
    GetNewsSourcesUsecase {
    override suspend fun invoke(category: String): Result<List<NewsSource>> {
        return withContext(coroutineDispatcher){
            newsSourceRepository.getNewsSourceByCategory(category)
        }
    }
}

