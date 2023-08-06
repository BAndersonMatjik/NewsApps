package com.bmatjik.myapplication.feature.usecase.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.bmatjik.myapplication.core.ArticlePagingFilteredSource
import com.bmatjik.myapplication.core.ArticlePagingSource
import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.usecase.GetArticlesByNewsSourceUsecase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class GetArticlesByNewsSourceUsecaseImpl @Inject constructor(
    private val articlePagingSource: ArticlePagingSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : GetArticlesByNewsSourceUsecase {
    override suspend fun invoke(newsSource: String): Flow<PagingData<Article>> {
       return Pager(
           // Configure how data should be loaded
           PagingConfig(pageSize = 10,enablePlaceholders = true),
       ){
           articlePagingSource.setNewsSource(newsSource)
           articlePagingSource
       }.flow.flowOn(coroutineDispatcher)
    }
}


@ViewModelScoped
class GetArticlesByNewsSourceFilterUsecaseImpl @Inject constructor(
    private val articlePagingSource: ArticlePagingFilteredSource,
    private val coroutineDispatcher: CoroutineDispatcher
) : GetArticlesByNewsSourceFilterUsecase {
    override suspend fun invoke(newsSource: String,search:String?): Flow<PagingData<Article>> {

        return Pager(
            // Configure how data should be loaded
            PagingConfig(pageSize = 10,enablePlaceholders = true),
        ){
            articlePagingSource.setNewsSource(newsSource)
            articlePagingSource.setArticle(search)
            articlePagingSource
        }.flow.flowOn(coroutineDispatcher)

    }
}

interface GetArticlesByNewsSourceFilterUsecase {
    suspend operator fun invoke(newsSource: String,search: String?): Flow<PagingData<Article>>
}