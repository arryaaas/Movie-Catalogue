package com.arya.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arya.moviecatalogue.data.source.MovieRepository
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.vo.Resource

class DetailViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val movieId = MutableLiveData<Int>()
    private val tvId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setSelectedTv(tvId: Int) {
        this.tvId.value = tvId
    }

    var movieDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(movieId) { movieId ->
            movieRepository.getMovieDetail(movieId)
        }

    var tvDetail: LiveData<Resource<MovieEntity>> =
        Transformations.switchMap(tvId) { tvId ->
            movieRepository.getTvDetail(tvId)
        }

    fun setFavoriteMovie() {
        val movieEntity = movieDetail.value?.data
        if (movieEntity != null) {
            val newState = !movieEntity.isFavorite
            movieRepository.setFavorite(movieEntity, newState)
        }
    }

    fun setFavoriteTv() {
        val tvEntity = tvDetail.value?.data
        if (tvEntity != null) {
            val newState = !tvEntity.isFavorite
            movieRepository.setFavorite(tvEntity, newState)
        }
    }

}