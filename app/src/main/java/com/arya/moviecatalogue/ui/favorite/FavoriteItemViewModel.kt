package com.arya.moviecatalogue.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteItemViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    fun getFavMovie(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavMovie()

    fun getFavTv(): LiveData<PagedList<MovieEntity>> = movieRepository.getFavTv()

}