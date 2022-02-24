package com.arya.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvResult (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("first_air_date")
    val firstAirDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Float,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("backdrop_path")
    val backdropPath: String,

)
