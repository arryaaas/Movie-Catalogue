package com.arya.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.arya.moviecatalogue.data.source.local.LocalDataSource
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.data.source.remote.ApiResponse
import com.arya.moviecatalogue.data.source.remote.RemoteDataSource
import com.arya.moviecatalogue.data.source.remote.response.*
import com.arya.moviecatalogue.utils.AppExecutors
import com.arya.moviecatalogue.vo.Resource

class MovieRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieDataSource {

    override fun getMovie(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResult>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovie(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResult>>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: List<MovieResult>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        id = response.id,
                        title = response.title,
                        overview = response.overview,
                        releaseDate = response.releaseDate,
                        rating = response.voteAverage,
                        posterPath = response.posterPath,
                        backdropPath = response.backdropPath,
                        genres = "",
                        runtime = 0,
                        language = "",
                        homepage = "",
                        isFavorite = false,
                        type = "movie"
                    )
                    movieList.add(movie)
                }
                localDataSource.insertData(movieList)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null
                        && data.genres == ""
                        && data.runtime == 0
                        && data.language == ""
                        && data.homepage == ""

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val listGenres = ArrayList<String>()

                for (genre in data.genres) {
                    listGenres.add(genre.name)
                }

                val movie = MovieEntity(
                    id = data.id,
                    title = data.title,
                    overview = data.overview,
                    releaseDate = data.releaseDate,
                    rating = data.voteAverage,
                    posterPath = data.posterPath,
                    backdropPath = data.backdropPath,
                    genres = listGenres.joinToString(separator = ", "),
                    runtime = data.runtime,
                    language = data.spokenLanguages[0].englishName,
                    homepage = data.homepage,
                    isFavorite = false,
                    type = "movie"
                )

                localDataSource.updateData(movie)
            }

        }.asLiveData()
    }

    override fun getTv(sort: String): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<TvResult>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTv(sort), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvResult>>> =
                remoteDataSource.getTv()

            override fun saveCallResult(data: List<TvResult>) {
                val tvList = ArrayList<MovieEntity>()
                for (response in data) {
                    val tv = MovieEntity(
                        id = response.id,
                        title = response.name,
                        overview = response.overview,
                        releaseDate = response.firstAirDate,
                        rating = response.voteAverage,
                        posterPath = response.posterPath,
                        backdropPath = response.backdropPath,
                        genres = "",
                        runtime = 0,
                        language = "",
                        homepage = "",
                        isFavorite = false,
                        type = "tv"
                    )
                    tvList.add(tv)
                }
                localDataSource.insertData(tvList)
            }

        }.asLiveData()
    }

    override fun getTvDetail(tvId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, TvDetailResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getTvDetail(tvId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data != null
                        && data.genres == ""
                        && data.runtime == 0
                        && data.language == ""
                        && data.homepage == ""

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> =
                remoteDataSource.getTvDetail(tvId)

            override fun saveCallResult(data: TvDetailResponse) {
                val listGenres = ArrayList<String>()

                for (genre in data.genres) {
                    listGenres.add(genre.name)
                }

                val tv = MovieEntity(
                    id = data.id,
                    title = data.name,
                    overview = data.overview,
                    releaseDate = data.firstAirDate,
                    rating = data.voteAverage,
                    posterPath = data.posterPath,
                    backdropPath = data.backdropPath,
                    genres = listGenres.joinToString(separator = ", "),
                    runtime = data.runtime.average().toInt(),
                    language = data.spokenLanguages[0].englishName,
                    homepage = data.homepage,
                    isFavorite = false,
                    type = "tv"
                )

                localDataSource.updateData(tv)
            }

        }.asLiveData()
    }

    override fun getFavMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovie(), config).build()
    }

    override fun getFavTv(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTv(), config).build()
    }

    override fun setFavorite(movie: MovieEntity, state: Boolean) {
        return appExecutors.diskIO().execute {
            localDataSource.setDataFavorite(movie, state)
        }
    }

}