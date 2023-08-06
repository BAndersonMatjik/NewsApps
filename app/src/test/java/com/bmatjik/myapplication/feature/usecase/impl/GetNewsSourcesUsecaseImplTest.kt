package com.bmatjik.myapplication.feature.usecase.impl

import com.bmatjik.myapplication.core.repository.NewsRepository
import com.bmatjik.myapplication.feature.model.NewsSource
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetNewsSourcesUsecaseImplTest {

    @MockK
    lateinit var newsSourceRepository:NewsRepository

    @SpyK
    var dispatcher:CoroutineDispatcher = Dispatchers.Default

    @InjectMockKs
    lateinit var getNewsSourcesUsecaseImpl: GetNewsSourcesUsecaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun given_category_return_success(){
        coEvery {
            newsSourceRepository.getNewsSourcesByCategory("technology")
        }returns Result.success(listOf(NewsSource(
            category = "blandit",
            country = "Cameroon",
            description = "esse",
            id = "decore",
            language = "vel",
            name = "Emilia Green"
        )))
        runTest {
            getNewsSourcesUsecaseImpl("technology").apply {
                println(this)
                Truth.assertThat(this.isSuccess).isEqualTo(true)
                Truth.assertThat(this.getOrNull()?.size).isEqualTo(1)
                Truth.assertThat(this.getOrNull()?.first()?.name).isEqualTo("Emilia Green")
            }
        }
    }
    @Test
    fun given_category_return_error(){
        coEvery {
            newsSourceRepository.getNewsSourcesByCategory("technology")
        }returns Result.failure(
            Exception("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.")
        )
        runTest {
            getNewsSourcesUsecaseImpl("technology").apply {
                println(this)
                Truth.assertThat(this.isFailure).isEqualTo(true)
                Truth.assertThat(this.exceptionOrNull()?.message).isEqualTo("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.")
            }
        }
    }
}