package com.bmatjik.myapplication.feature.usecase

interface GetCategoryUseCase{
    suspend fun execute():List<String>
}