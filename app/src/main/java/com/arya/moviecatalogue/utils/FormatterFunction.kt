package com.arya.moviecatalogue.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun formatToolbarTitle(type: String): String = when (type) {
    "movie" -> "Movie Detail"
    "tv" -> "Tv Show Detail"
    else -> ""
}

fun formatDate(date: String, format: String): String {
    var formattedDate = ""
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC")

    try {
        sdf.parse(date)?.let {
            formattedDate = SimpleDateFormat(format, Locale.getDefault()).format(it)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}

fun formatRating(rating: Float): String = "$rating / 10"

fun formatRuntime(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60
    return when {
        hours == 0 -> "${minutes}min"
        minutes == 0 -> "${hours}h"
        else -> "${hours}h ${minutes}min"
    }
}

fun formatOverview(overview: String): String = if (overview.isEmpty()) {
    "Sorry, the synopsis is not yet available"
} else {
    overview
}