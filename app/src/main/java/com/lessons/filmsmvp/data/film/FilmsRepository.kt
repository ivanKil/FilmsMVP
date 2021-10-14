package com.lessons.filmsmvp.data.film

import io.reactivex.rxjava3.core.Observable

interface FilmsRepository {
    fun getPlayingNow(page: Int, query: String? = null): Observable<FilmsResponse>
    fun getFilmDetails(filmId: Int, append_to_response: String = "credits"): Observable<FilmDetail>
}