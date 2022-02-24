package com.arya.moviecatalogue.network

import com.arya.moviecatalogue.BuildConfig
import com.arya.moviecatalogue.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/3/discover/movie")
    fun getMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<MovieResponse>

    @GET("/3/discover/tv")
    fun getTv(
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<TvResponse>

    @GET("/3/movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id")
        movieId: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<MovieDetailResponse>

    @GET("/3/tv/{tv_id}")
    fun getDetailTv(
        @Path("tv_id")
        tvId: Int,
        @Query("api_key")
        apiKey: String = BuildConfig.API_KEY
    ): Call<TvDetailResponse>

}