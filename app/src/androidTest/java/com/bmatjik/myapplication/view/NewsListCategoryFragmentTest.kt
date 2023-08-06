package com.bmatjik.myapplication.view

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnitRunner
import com.bmatjik.myapplication.R
import com.bmatjik.myapplication.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.hamcrest.CoreMatchers.allOf
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


    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext())

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun start_and_load_category(){
        navController.setGraph(R.navigation.nav_graph)

        launchFragmentInHiltContainer<NewsListCategoryFragment> {
            viewLifecycleOwnerLiveData.observeForever {
                it?.let {
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
        onView(allOf(withId(R.id.rv_category), isDisplayed()))
            .perform(actionOnItem<RecyclerView.ViewHolder>(withChild(withText("business")), click()));
    }
}

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}