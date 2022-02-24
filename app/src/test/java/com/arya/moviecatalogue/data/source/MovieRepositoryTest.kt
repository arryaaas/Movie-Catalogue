package com.arya.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.arya.moviecatalogue.data.source.local.LocalDataSource
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.data.source.remote.RemoteDataSource
import com.arya.moviecatalogue.utils.AppExecutors
import com.arya.moviecatalogue.utils.DataDummy
import com.arya.moviecatalogue.utils.LiveDataTestUtil
import com.arya.moviecatalogue.utils.PagedListUtil
import com.arya.moviecatalogue.utils.SortUtils.RELEASE_DATE
import com.arya.moviecatalogue.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@Suppress("UNCHECKED_CAST")
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieRepository = FakeMovieRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovies()
    private val movieId = movieResponses[0].id
    private val movieDetail = DataDummy.generateRemoteDummyMovieDetail()
    private val tvResponses = DataDummy.generateRemoteDummyTv()
    private val tvId = tvResponses[0].id
    private val tvDetail = DataDummy.generateRemoteDummyTvDetail()

    @Test
    fun getMovie() {
        val dummyMovies = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovie(RELEASE_DATE)).thenReturn(dummyMovies)
        movieRepository.getMovie(RELEASE_DATE)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovie()))
        verify(local).getMovie(RELEASE_DATE)
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getMovieDetail() {
        val dummyMovieDetail = MutableLiveData<MovieEntity>()
        dummyMovieDetail.value = DataDummy.generateDummyMovieDetail()
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyMovieDetail)

        val movieDetailEntities = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieDetailEntities.data)
        assertEquals(movieDetail.id, movieDetailEntities.data?.id)
    }

    @Test
    fun getTv() {
        val dummyTv = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getTv(RELEASE_DATE)).thenReturn(dummyTv)
        movieRepository.getTv(RELEASE_DATE)

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTv()))
        verify(local).getTv(RELEASE_DATE)
        assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getTvDetail() {
        val dummyTvDetail = MutableLiveData<MovieEntity>()
        dummyTvDetail.value = DataDummy.generateDummyTvDetail()
        `when`(local.getTvDetail(tvId)).thenReturn(dummyTvDetail)

        val tvDetailEntities = LiveDataTestUtil.getValue(movieRepository.getTvDetail(tvId))
        verify(local).getTvDetail(tvId)
        assertNotNull(tvDetailEntities.data)
        assertEquals(tvDetail.id, tvDetailEntities.data?.id)
    }
}