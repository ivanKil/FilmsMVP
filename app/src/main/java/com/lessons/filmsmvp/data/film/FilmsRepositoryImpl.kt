package com.lessons.filmsmvp.data.film

import com.lessons.filmsmvp.data.film.datasource.FilmsDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val cloud: FilmsDataSource/*, private val networkStatus: INetworkStatus,
    private val cache: CacheUserDataSource*/
) : FilmsRepository {
    override fun getPlayingNow(page: Int, query: String?): Observable<FilmsResponse> {
        return cloud.getPlayingNow(page, query)
    }

    override fun getFilmDetails(filmId: Int, append_to_response: String): Observable<FilmDetail> {
        return cloud.getFilmDetails(filmId, append_to_response)
    }


}