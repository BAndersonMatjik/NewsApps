package com.bmatjik.myapplication.feature.usecase

import com.bmatjik.myapplication.feature.model.NewsSource

interface GetNewsSourcesUsecase {
    suspend operator fun invoke(category:String):Result<List<NewsSource>>
}