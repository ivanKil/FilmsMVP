package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.api.FilmsApi
import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmDetail
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CloudFilmsDataSource @Inject constructor(private val filmsApi: FilmsApi) : FilmsDataSource {
    override fun getPlayingNow(page: Int, query: String?): Single<List<Film>> {
        return filmsApi.getPlayingNow(page, query)
            .flatMap { res -> Single.fromCallable { res.results } }
    }

    override fun getFilmDetails(filmId: Int): Single<FilmDetail> {
        return filmsApi.getFilmDetails(filmId)
    }
}