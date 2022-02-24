package com.arya.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.vo.Resource

interface MovieDataSource {

    fun getMovie(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTv(sort: String): LiveData<Resource<PagedList<MovieEntity>>>

    fun getTvDetail(tvId: Int): LiveData<Resource<MovieEntity>>

    fun getFavMovie(): LiveData<PagedList<MovieEntity>>

    fun getFavTv(): LiveData<PagedList<MovieEntity>>

    fun setFavorite(movie: MovieEntity, state: Boolean)

}