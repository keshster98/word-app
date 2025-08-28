package com.team.wordapp.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun dateTimeFormatter(dateTime: ZonedDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("d MM hh:mm a", Locale.ENGLISH)
    return dateTime.format(formatter)
}