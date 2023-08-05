package com.bmatjik.myapplication.feature.usecase.impl

import com.bmatjik.myapplication.common.Constants
import com.bmatjik.myapplication.feature.usecase.GetCategoryUsecase
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
class GetCategoryUsecaseImpl: GetCategoryUsecase {
    override suspend fun execute(): List<String> {
        return Constants.NewsCategory
    }
}

