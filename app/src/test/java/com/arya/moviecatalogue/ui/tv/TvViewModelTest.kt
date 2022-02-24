package com.arya.moviecatalogue.ui.tv

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
class TvViewModelTest {

    private lateinit var viewModel: TvViewModel

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
        viewModel = TvViewModel(movieRepository)
    }

    @Test
    fun getTv() {
        val dummyTv = Resource.success(pagedList)
        `when`(dummyTv.data?.size).thenReturn(1)
        val tv = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        tv.value = dummyTv

        `when`(movieRepository.getTv(RELEASE_DATE)).thenReturn(tv)
        val tvEntities = viewModel.getTv(RELEASE_DATE).value?.data
        verify(movieRepository).getTv(RELEASE_DATE)
        assertNotNull(tvEntities)
        assertEquals(1, tvEntities?.size)

        viewModel.getTv(RELEASE_DATE).observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }

}