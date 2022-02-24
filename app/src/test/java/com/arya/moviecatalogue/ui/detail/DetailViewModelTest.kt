package com.arya.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.utils.DataDummy
import com.arya.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val movieId = dummyMovie.id
    private val dummyMovieDetail = DataDummy.generateDummyMovieDetail()

    private val dummyTv = DataDummy.generateDummyTv()[0]
    private val tvId = dummyTv.id
    private val dummyTvDetail = DataDummy.generateDummyTvDetail()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(movieRepository)
        movieId?.let { viewModel.setSelectedMovie(it) }
        tvId?.let { viewModel.setSelectedTv(it) }
    }

    @Test
    fun getMovieDetail() {
        val movieDetail = Resource.success(dummyMovieDetail)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieDetail
        `when`(movieId?.let { movieRepository.getMovieDetail(it) }).thenReturn(movie)
        viewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(movieDetail)
    }

    @Test
    fun getTvDetail() {
        val tvDetail = Resource.success(dummyTvDetail)
        val tv = MutableLiveData<Resource<MovieEntity>>()
        tv.value = tvDetail
        `when`(tvId?.let { movieRepository.getTvDetail(it) }).thenReturn(tv)
        viewModel.tvDetail.observeForever(observer)
        verify(observer).onChanged(tvDetail)
    }

    @Test
    fun setFavoriteMovie() {
        val movieDetail = Resource.success(dummyMovieDetail)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieDetail
        `when`(movieId?.let { movieRepository.getMovieDetail(it) }).thenReturn(movie)
        viewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(movieDetail)

        val movieDetailData = viewModel.movieDetail.value?.data as MovieEntity
        viewModel.setFavoriteMovie()
        verify(movieRepository).setFavorite(movieDetailData, true)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun deleteFavoriteMovie() {
        val movieDetail = Resource.success(dummyMovieDetail)
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = movieDetail
        `when`(movieId?.let { movieRepository.getMovieDetail(it) }).thenReturn(movie)
        viewModel.movieDetail.observeForever(observer)
        verify(observer).onChanged(movieDetail)

        val movieDetailData = viewModel.movieDetail.value?.data as MovieEntity
        movieDetailData.isFavorite = true

        viewModel.setFavoriteMovie()
        verify(movieRepository).setFavorite(movieDetailData, false)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun setFavoriteTv() {
        val tvDetail = Resource.success(dummyTvDetail)
        val tv = MutableLiveData<Resource<MovieEntity>>()
        tv.value = tvDetail
        `when`(tvId?.let { movieRepository.getTvDetail(it) }).thenReturn(tv)
        viewModel.tvDetail.observeForever(observer)
        verify(observer).onChanged(tvDetail)

        val tvDetailData = viewModel.tvDetail.value?.data as MovieEntity
        viewModel.setFavoriteTv()
        verify(movieRepository).setFavorite(tvDetailData, true)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun deleteFavoriteTv() {
        val tvDetail = Resource.success(dummyTvDetail)
        val tv = MutableLiveData<Resource<MovieEntity>>()
        tv.value = tvDetail
        `when`(tvId?.let { movieRepository.getTvDetail(it) }).thenReturn(tv)
        viewModel.tvDetail.observeForever(observer)
        verify(observer).onChanged(tvDetail)

        val tvDetailData = viewModel.tvDetail.value?.data as MovieEntity
        tvDetailData.isFavorite = true

        viewModel.setFavoriteTv()
        verify(movieRepository).setFavorite(tvDetailData, false)
        verifyNoMoreInteractions(observer)
    }
}