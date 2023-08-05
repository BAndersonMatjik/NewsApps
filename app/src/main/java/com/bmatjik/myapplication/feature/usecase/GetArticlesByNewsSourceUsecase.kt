package com.bmatjik.myapplication.feature.usecase

import com.bmatjik.myapplication.feature.model.Article

interface GetArticlesByNewsSourceUsecase {
    suspend operator fun invoke(newsSource: String):Result<List<Article>>
}