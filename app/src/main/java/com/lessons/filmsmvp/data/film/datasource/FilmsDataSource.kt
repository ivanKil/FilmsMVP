package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.film.FilmDetail
import com.lessons.filmsmvp.data.film.FilmsResponse
import io.reactivex.rxjava3.core.Observable

interface FilmsDataSource {
    fun getPlayingNow(page: Int, query: String? = null): Observable<FilmsResponse>
    fun getFilmDetails(filmId: Int, append_to_response: String = "credits"): Observable<FilmDetail>
}