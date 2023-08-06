package com.bmatjik.myapplication.view

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import com.bmatjik.myapplication.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@SmallTest
@HiltAndroidTest
class NewsListCategoryFragmentTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // single task rule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun start_and_load_category(){
        launchFragmentInHiltContainer<NewsListCategoryFragment> {


        }
    }
}

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}