package com.team.wordapp.data.db

import androidx.room.TypeConverter
import java.time.ZonedDateTime

class Converters {
    // Db automatically detects ZonedDateTime in word and calls either function accordingly for date storage/usage
    @TypeConverter
    fun fromZonedDateTime(zdt: ZonedDateTime?): String? {
        return zdt?.toString() // ISO-8601 string
    }
    @TypeConverter
    fun toZonedDateTime(value: String?): ZonedDateTime? {
        return value?.let { ZonedDateTime.parse(it) }
    }
}