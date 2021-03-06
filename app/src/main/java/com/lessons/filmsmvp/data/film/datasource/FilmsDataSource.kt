package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmDetail
import io.reactivex.rxjava3.core.Single

interface FilmsDataSource {
    fun getPlayingNow(page: Int, query: String? = null): Single<List<Film>>
    fun getFilmDetails(filmId: Int): Single<FilmDetail>
}