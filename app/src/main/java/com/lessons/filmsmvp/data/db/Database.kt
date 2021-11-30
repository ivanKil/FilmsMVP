package com.lessons.filmsmvp.data.db

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lessons.filmsmvp.data.db.film.FilmDao
import com.lessons.filmsmvp.data.db.film.FilmDetailDao
import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmDetail


@androidx.room.Database(entities = [Film::class, FilmDetail::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database : RoomDatabase() {
    abstract val filmDao: FilmDao
    abstract val filmDetailDao: FilmDetailDao
}