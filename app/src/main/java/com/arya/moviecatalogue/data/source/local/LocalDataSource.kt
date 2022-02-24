package com.arya.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.data.source.local.room.MovieDao
import com.arya.moviecatalogue.utils.SortUtils

class LocalDataSource(private val mMovieDao: MovieDao) {

    fun getMovie(sort: String): DataSource.Factory<Int, MovieEntity> = mMovieDao.getMovie(SortUtils.getSortedQueryMovies(sort))

    fun getTv(sort: String): DataSource.Factory<Int, MovieEntity> = mMovieDao.getTv(SortUtils.getSortedQueryTv(sort))

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> = mMovieDao.getMovieDetail(movieId)

    fun getTvDetail(tvId: Int): LiveData<MovieEntity> = mMovieDao.getTvDetail(tvId)

    fun insertData(movies: List<MovieEntity>) = mMovieDao.insertData(movies)

    fun updateData(movie: MovieEntity) = mMovieDao.updateData(movie)

    fun getFavMovie(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavMovie()

    fun getFavTv(): DataSource.Factory<Int, MovieEntity> = mMovieDao.getFavTv()

    fun setDataFavorite(movie: MovieEntity, state: Boolean) {
        movie.isFavorite = state
        mMovieDao.updateData(movie)
    }

}