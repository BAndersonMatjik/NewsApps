package com.bmatjik.myapplication.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmatjik.myapplication.feature.model.NewsSource
import com.bmatjik.myapplication.feature.usecase.GetNewsSourcesUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourcesViewModel @Inject constructor(
    private val getNewsSourcesUsecase: GetNewsSourcesUsecase
): ViewModel() {
    private val _uiState = MutableStateFlow<NewsSourcesUiState>(NewsSourcesUiState())
    val uiState: StateFlow<NewsSourcesUiState> = _uiState.asStateFlow()

    fun getNewsSources(category:String){
        _uiState.value = _uiState.value.copy(true, error = "")
        viewModelScope.launch {
            getNewsSourcesUsecase(category).fold(onFailure = {
                _uiState.value = _uiState.value.copy(false, error = it.message?:"error")
            }, onSuccess = {
                _uiState.value = _uiState.value.copy(isLoading = false, newsSources = it)
            })
        }
    }



    data class NewsSourcesUiState(
        val isLoading:Boolean= false,
        val newsSources:List<NewsSource> = arrayListOf(),
        var error:String = ""
    )
}