package com.arya.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.arya.moviecatalogue.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getMovie(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @RawQuery(observedEntities = [MovieEntity::class])
    fun getTv(query: SupportSQLiteQuery): DataSource.Factory<Int, MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    @Transaction
    @Query("SELECT * FROM movie_entities WHERE id = :tvId")
    fun getTvDetail(tvId: Int): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(movies: List<MovieEntity>)

    @Update
    fun updateData(movie: MovieEntity)

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1 AND type = 'movie'")
    fun getFavMovie(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movie_entities WHERE isFavorite = 1 AND type = 'tv'")
    fun getFavTv(): DataSource.Factory<Int, MovieEntity>

}