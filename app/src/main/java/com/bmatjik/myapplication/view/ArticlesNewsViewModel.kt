package com.bmatjik.myapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bmatjik.myapplication.feature.model.Article
import com.bmatjik.myapplication.feature.usecase.GetArticlesByNewsSourceUsecase
import com.bmatjik.myapplication.feature.usecase.impl.GetArticlesByNewsSourceFilterUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticlesNewsViewModel @Inject constructor(private val getArticlesByNewsSourceUsecase: GetArticlesByNewsSourceUsecase,private val getArticlesByNewsSourceFilterUsecase: GetArticlesByNewsSourceFilterUsecase) : ViewModel() {
    var category:String = ""
    var articles : Flow<PagingData<Article>>? = null
    var newsSource = ""
    fun getArticles(search:String?){
        viewModelScope.launch {
            articles = getArticlesByNewsSourceUsecase(newsSource).cachedIn(viewModelScope)
        }
    }

    fun getFilterArticles(search: String?){
        viewModelScope.launch {
            articles = getArticlesByNewsSourceFilterUsecase(newsSource,search).cachedIn(viewModelScope)
        }
    }
}