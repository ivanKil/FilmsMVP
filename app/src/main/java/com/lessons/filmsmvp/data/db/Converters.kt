package com.lessons.filmsmvp.data.db

import androidx.room.TypeConverter
import com.lessons.filmsmvp.data.film.Genre
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromStringList(hobbies: List<Genre>): String? {
        return hobbies.map { "${it.id},${it.name}" }.joinToString("|")
    }

    @TypeConverter
    fun toStringList(data: String?): List<Genre>? {
        return data?.split("|")?.map {
            val arr = it.split(",")
            Genre(arr[0].toInt(), arr[1])
        } ?: listOf()
    }
}