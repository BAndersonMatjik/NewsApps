package com.bmatjik.myapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.usecase.GetArticlesByNewsSourceUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesNewsViewModel @Inject constructor(private val getArticlesByNewsSourceUsecase: GetArticlesByNewsSourceUsecase) : ViewModel() {

    var articles : Flow<PagingData<Article>>? = null
    var newsSource = "abc-news"
    fun getArticles(){
        viewModelScope.launch {
            articles = getArticlesByNewsSourceUsecase(newsSource).cachedIn(viewModelScope)
        }
    }
}