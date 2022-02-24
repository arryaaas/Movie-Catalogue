package com.arya.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.vo.Resource

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getMovie(sort: String): LiveData<Resource<PagedList<MovieEntity>>> =
        movieRepository.getMovie(sort)

}