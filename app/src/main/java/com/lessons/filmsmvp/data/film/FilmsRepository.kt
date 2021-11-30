package com.lessons.filmsmvp.data.film

import io.reactivex.rxjava3.core.Single

interface FilmsRepository {
    fun getPlayingNow(page: Int, query: String? = null): Single<List<Film>>
    fun getFilmDetails(filmId: Int): Single<FilmDetail>
}