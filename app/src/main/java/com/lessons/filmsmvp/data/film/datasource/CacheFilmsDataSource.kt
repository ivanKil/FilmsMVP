package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmDetail
import io.reactivex.rxjava3.core.Single

interface CacheFilmsDataSource {
    fun getPlayingNow(): Single<List<Film>>
    fun getFilmDetails(filmId: Int): Single<FilmDetail>

    fun retainFilms(films: List<Film>): Single<List<Film>>
    fun retainFilmDetail(filmDetail: FilmDetail): Single<FilmDetail>
}