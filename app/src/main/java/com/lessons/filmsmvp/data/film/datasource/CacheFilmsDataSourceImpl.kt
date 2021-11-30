package com.lessons.filmsmvp.data.film.datasource

import com.lessons.filmsmvp.data.db.Database
import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmDetail
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CacheFilmsDataSourceImpl @Inject constructor(private val db: Database) :
    CacheFilmsDataSource {
    override fun getPlayingNow(): Single<List<Film>> {
        return db.filmDao.getAll()
    }

    override fun getFilmDetails(filmId: Int): Single<FilmDetail> {
        return Single.fromCallable { db.filmDetailDao.findByFilmId(filmId) }
    }

    override fun retainFilms(films: List<Film>): Single<List<Film>> {
        db.filmDao.insert(films)
        return Single.fromCallable { films }
    }

    override fun retainFilmDetail(filmDetail: FilmDetail): Single<FilmDetail> {
        db.filmDetailDao.insert(filmDetail)
        return Single.fromCallable { filmDetail }
    }

}