package com.arya.moviecatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteItemViewModelTest {

    private lateinit var viewModel: FavoriteItemViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = FavoriteItemViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(1)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieRepository.getFavMovie()).thenReturn(movies)
        val movieEntities = viewModel.getFavMovie().value
        verify(movieRepository).getFavMovie()
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        viewModel.getFavMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun getFavoriteTv() {
        val dummyTv = pagedList
        `when`(dummyTv.size).thenReturn(1)
        val tv = MutableLiveData<PagedList<MovieEntity>>()
        tv.value = dummyTv

        `when`(movieRepository.getFavTv()).thenReturn(tv)
        val tvEntities = viewModel.getFavTv().value
        verify(movieRepository).getFavTv()
        assertNotNull(tvEntities)
        assertEquals(1, tvEntities?.size)

        viewModel.getFavTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}