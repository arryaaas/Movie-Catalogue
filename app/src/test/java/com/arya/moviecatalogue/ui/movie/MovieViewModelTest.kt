package com.arya.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.utils.SortUtils.RELEASE_DATE
import com.arya.moviecatalogue.vo.Resource
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
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(1)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(movieRepository.getMovie(RELEASE_DATE)).thenReturn(movies)
        val movieEntities = viewModel.getMovie(RELEASE_DATE).value?.data
        verify(movieRepository).getMovie(RELEASE_DATE)
        assertNotNull(movieEntities)
        assertEquals(1, movieEntities?.size)

        viewModel.getMovie(RELEASE_DATE).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

}