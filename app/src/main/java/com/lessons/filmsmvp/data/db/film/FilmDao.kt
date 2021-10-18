package com.lessons.filmsmvp.data.db.film

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lessons.filmsmvp.data.film.Film
import io.reactivex.rxjava3.core.Single

@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(films: List<Film>)

    @Query("SELECT * FROM Film")
    fun getAll(): Single<List<Film>>
}