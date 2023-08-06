package com.bmatjik.myapplication.core

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.feature.model.Article
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ArticlePagingFilteredSource @Inject constructor(private val newsRepository:NewsRepository): PagingSource<Int,Article>() {

    private var newsSource:String = ""
    private var article:String?=null
    fun setNewsSource(newsSource: String){
        this.newsSource = newsSource
    }
    fun setArticle(article: String?){
        this.article = article

    }
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: Constants.NEWSAPI_STARTING_PAGE_INDEX
        val nextPage = params.key ?: 1
        val previousIndex = if (nextPage-1> 1) nextPage-1 else null
        try {
            newsRepository.getArticlesByNewsSource(newsSource,Constants.NEWSAPI_PAGE_SIZE.toString(),pageIndex.toString(),article).fold(
                onFailure = {
                    return LoadResult.Error(it)
                },
                onSuccess = {
                    return LoadResult.Page(
                        it,previousIndex,nextPage+1
                    )
                }
            )

        }catch (e:Exception){
            return LoadResult.Error(e)
        }

    }

}