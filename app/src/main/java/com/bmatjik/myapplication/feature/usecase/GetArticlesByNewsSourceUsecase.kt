package com.bmatjik.myapplication.feature.usecase

import androidx.paging.PagingData
import com.bmatjik.myapplication.feature.model.Article
import kotlinx.coroutines.flow.Flow

interface GetArticlesByNewsSourceUsecase {
    suspend operator fun invoke(newsSource: String): Flow<PagingData<Article>>
}