package com.arya.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.arya.moviecatalogue.R
import com.arya.moviecatalogue.utils.*
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadEmptyFavoriteMovieAndTvShow() {
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.notify_layout)).check(matches(isDisplayed()))
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.notify_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDataMovieAndSort() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))

        onView(withId(R.id.action_sort)).check(matches(isDisplayed()))
        onView(withId(R.id.action_sort)).perform(click())
        onView(withText("Release Date")).perform(click())

        onView(withId(R.id.action_sort)).check(matches(isDisplayed()))
        onView(withId(R.id.action_sort)).perform(click())
        onView(withText("Rating")).perform(click())

        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
    }

    @Test
    fun shareMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_share)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share)).perform(click())
    }

    @Test
    fun addAndDeleteFavoriteMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.notify_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun loadDataTvShowAndSort() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))

        onView(withId(R.id.action_sort)).check(matches(isDisplayed()))
        onView(withId(R.id.action_sort)).perform(click())
        onView(withText("Release Date")).perform(click())

        onView(withId(R.id.action_sort)).check(matches(isDisplayed()))
        onView(withId(R.id.action_sort)).perform(click())
        onView(withText("Rating")).perform(click())

        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                19
            )
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.collapsing_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genres)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_runtime)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
    }

    @Test
    fun shareTvShow() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_share)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_share)).perform(click())
    }

    @Test
    fun addAndDeleteFavoriteTvShow() {
        onView(withId(R.id.navigation_tv)).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_favorite)).perform(click())
        pressBack()

        onView(withId(R.id.notify_layout)).check(matches(isDisplayed()))
    }
}