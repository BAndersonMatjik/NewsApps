package com.bmatjik.myapplication.feature.usecase

interface GetCategoryUsecase{
    suspend fun execute():List<String>
}