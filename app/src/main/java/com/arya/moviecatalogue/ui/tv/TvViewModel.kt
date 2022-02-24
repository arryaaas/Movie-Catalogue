package com.arya.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.vo.Resource

class TvViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getTv(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getTv(sort)

}