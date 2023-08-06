package com.bmatjik.myapplication.core.repository.impl

import com.bmatjik.myapplication.core.remote.api.NewsApi
import com.bmatjik.myapplication.core.remote.model.ArticleResponse
import com.bmatjik.myapplication.core.remote.model.ArticlesResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourceResponse
import com.bmatjik.myapplication.core.remote.model.NewsSourcesResponse
import com.bmatjik.myapplication.core.remote.model.SourceResponse
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class NewsRepositoryImplTest {

    @MockK
    lateinit var newsApi: NewsApi

    @SpyK
    var moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @InjectMockKs
    lateinit var newsRepositoryImpl: NewsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun given_newsources_and_success() {
        coEvery {
            newsApi.getNewsSources("business")
        } returns Response.success(
            200, NewsSourcesResponse(
                "ok", sources = listOf(
                    NewsSourceResponse(
                        category = "fabulas",
                        country = "Korea, North",
                        description = "rutrum",
                        id = "assueverit",
                        language = "vocibus",
                        name = "Laurence Graham",
                        url = "https://duckduckgo.com/?q=aliquip"
                    )
                )
            )
        )
        runTest {
            newsRepositoryImpl.getNewsSourcesByCategory("business").apply {
                println(this)
                Truth.assertThat(this.getOrNull()?.size).isEqualTo(1)
                Truth.assertThat(this.getOrNull()?.first()?.name).isEqualTo("Laurence Graham")
            }
        }
    }

    @Test
    fun given_newsources_and_error() {
        coEvery {
            newsApi.getNewsSources("business")
        } returns Response.error(
            400, ResponseBody.create(
                "application/json".toMediaTypeOrNull(), "{\n" +
                        "\"status\": \"error\",\n" +
                        "\"code\": \"apiKeyMissing\",\n" +
                        "\"message\": \"Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.\"\n" +
                        "}"
            )
        )
        runTest {
            newsRepositoryImpl.getNewsSourcesByCategory("business").apply {
                println(this)
                Truth.assertThat(this.isFailure).isEqualTo(true)
                Truth.assertThat(this.exceptionOrNull()?.message)
                    .isEqualTo("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.")
            }
        }
    }

    @Test
    fun given_article_and_success() {
        coEvery {
            newsApi.getArticlesByNewsSource("kompas", "10", "1", null)
        } returns Response.success(
            200, ArticlesResponse(
                articles = listOf(ArticleResponse(
                    author = "patrioque",
                    content = "conubia",
                    description = "duis",
                    publishedAt = "repudiandae",
                    source = SourceResponse(
                        id = "dis",
                        name = "Leslie Wall"
                    ),
                    title = "expetendis",
                    url = "https://search.yahoo.com/search?p=arcu",
                    urlToImage = "https://search.yahoo.com/search?p=eam"
                )),
                status = "ok",
                totalResults = 3000,
                code = null,
                message = null
            )
        )
        runTest {
            newsRepositoryImpl.getArticlesByNewsSource("kompas", "10", "1", null).apply {
                println(this)
                Truth.assertThat(this.getOrNull()?.size).isEqualTo(1)
                Truth.assertThat(this.getOrNull()?.first()?.author).isEqualTo("patrioque")
            }
        }
    }

    @Test
    fun given_article_and_error() {
        coEvery {
            newsApi.getArticlesByNewsSource("kompas", "10", "1", null)
        } returns Response.error(
            400, ResponseBody.create(
                "application/json".toMediaTypeOrNull(), "{\n" +
                        "\"status\": \"error\",\n" +
                        "\"code\": \"apiKeyMissing\",\n" +
                        "\"message\": \"Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.\"\n" +
                        "}"
            )
        )
        runTest {
            newsRepositoryImpl.getArticlesByNewsSource("kompas", "10", "1", null).apply {
                println(this)
                Truth.assertThat(this.isFailure).isEqualTo(true)
                Truth.assertThat(this.exceptionOrNull()?.message)
                    .isEqualTo("Your API key is missing. Append this to the URL with the apiKey param, or use the x-api-key HTTP header.")
            }
        }
    }
}