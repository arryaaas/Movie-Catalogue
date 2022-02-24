package com.arya.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @field:SerializedName("genres")
    val genres: List<Genre>,

    @field:SerializedName("runtime")
    val runtime: Int,

    @field:SerializedName("spoken_languages")
    val spokenLanguages: List<Language>,

    @field:SerializedName("homepage")
    val homepage: String,

)
