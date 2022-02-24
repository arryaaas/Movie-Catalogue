package com.arya.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {

    const val TITLE = "title"
    const val RELEASE_DATE = "release date"
    const val RATING = "rating"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where type = 'movie' ")
        when (filter) {
            TITLE -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RELEASE_DATE -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTv(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities where type = 'tv' ")
        when (filter) {
            TITLE -> {
                simpleQuery.append("ORDER BY title DESC")
            }
            RELEASE_DATE -> {
                simpleQuery.append("ORDER BY releaseDate DESC")
            }
            RATING -> {
                simpleQuery.append("ORDER BY rating DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

}