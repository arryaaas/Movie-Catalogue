package com.arya.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Float? = 0F,

    @ColumnInfo(name = "posterPath")
    var posterPath: String? = null,

    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = null,

    @ColumnInfo(name = "genres")
    var genres: String? = null,

    @ColumnInfo(name = "runtime")
    var runtime: Int? = 0,

    @ColumnInfo(name = "language")
    var language: String? = null,

    @ColumnInfo(name = "homepage")
    var homepage: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "type")
    var type: String? = null,

)