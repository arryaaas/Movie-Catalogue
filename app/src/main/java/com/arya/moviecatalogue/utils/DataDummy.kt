package com.arya.moviecatalogue.utils

import com.arya.moviecatalogue.data.source.local.entity.MovieEntity
import com.arya.moviecatalogue.data.source.remote.response.*

object DataDummy {

    fun generateDummyMovie(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.5F,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
                "",
                0,
                "",
                "",
                isFavorite = false,
                type = "movie"
            )
        )

        return movie
    }

    fun generateDummyMovieDetail(): MovieEntity {
        return MovieEntity(
            634649,
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5F,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            "Action, Adventure, Science Fiction",
            148,
            "English",
            "",
            isFavorite = false,
            type = "movie"
        )
    }

    fun generateDummyTv(): List<MovieEntity> {
        val tv = ArrayList<MovieEntity>()

        tv.add(
            MovieEntity(
                88329,
                "Hawkeye",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "2021-11-24",
                8.5F,
                "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
                "",
                0,
                "",
                "",
                isFavorite = false,
                type = "tv"
            )
        )

        return tv
    }

    fun generateDummyTvDetail(): MovieEntity {
        return MovieEntity(
            88329,
            "Hawkeye",
            "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            "2021-11-24",
            8.5F,
            "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
            "Action & Adventure, Drama",
            50,
            "English",
            "",
            isFavorite = false,
            type = "tv"
        )
    }

    fun generateRemoteDummyMovies(): List<MovieResult> {
        val movie = ArrayList<MovieResult>()

        movie.add(
            MovieResult(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.5F,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            )
        )

        return movie
    }

    fun generateRemoteDummyMovieDetail(): MovieDetailResponse {
        return MovieDetailResponse(
            634649,
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5F,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "/1Rr5SrvHxMXHu5RjKpaMba8VTzi.jpg",
            listOf(
                Genre(
                    28,
                    "Action"
                ),
                Genre(
                    12,
                    "Adventure"
                ),
                Genre(
                    878,
                    "Science Fiction"
                ),
            ),
            148,
            listOf(
                Language(
                    "English",
                ),
                Language(
                    "Tagalog",
                ),
            ),
            "https://www.spidermannowayhome.movie"
        )
    }

    fun generateRemoteDummyTv(): List<TvResult> {
        val tv = ArrayList<TvResult>()

        tv.add(
            TvResult(
                88329,
                "Hawkeye",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "2021-11-24",
                8.5F,
                "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
            )
        )

        return tv
    }

    fun generateRemoteDummyTvDetail(): TvDetailResponse {
        return TvDetailResponse(
            88329,
            "Hawkeye",
            "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            "2021-11-24",
            8.5F,
            "/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            "/1R68vl3d5s86JsS2NPjl8UoMqIS.jpg",
            listOf(
                Genre(
                    10759,
                    "Action & Adventure"
                ),
                Genre(
                    18,
                    "Drama"
                ),
            ),
            listOf(50),
            listOf(
                Language(
                    "English",
                ),
            ),
            "https://www.disneyplus.com/series/hawkeye/11Zy8m9Dkj5l"
        )
    }

}