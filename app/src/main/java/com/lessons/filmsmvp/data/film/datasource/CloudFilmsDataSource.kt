package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.api.FilmsApi
import com.lessons.filmsmvp.data.film.FilmDetail
import com.lessons.filmsmvp.data.film.FilmsResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class CloudFilmsDataSource @Inject constructor(private val filmsApi: FilmsApi) : FilmsDataSource {
    override fun getPlayingNow(page: Int, query: String?): Observable<FilmsResponse> {
        return filmsApi.getPlayingNow(page, query)
    }

    override fun getFilmDetails(filmId: Int, append_to_response: String): Observable<FilmDetail> {
        return filmsApi.getFilmDetails(filmId, append_to_response)
    }


}