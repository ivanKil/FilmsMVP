package com.lessons.filmsmvp.data.db.film

import androidx.room.*
import com.lessons.filmsmvp.data.film.FilmDetail

@Dao
interface FilmDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(filmDetail: FilmDetail)

    @Update
    fun update(filmDetail: FilmDetail)

    @Query("SELECT * FROM FilmDetail WHERE id = :filmId")
    fun findByFilmId(filmId: Int): FilmDetail
}

