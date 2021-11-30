package com.lessons.filmsmvp.data.film

import com.lessons.filmsmvp.data.film.datasource.CacheFilmsDataSource
import com.lessons.filmsmvp.data.film.datasource.FilmsDataSource
import com.lessons.filmsmvp.data.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val cloud: FilmsDataSource, private val networkStatus: INetworkStatus,
    private val cache: CacheFilmsDataSource
) : FilmsRepository {
    override fun getPlayingNow(page: Int, query: String?) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cloud.getPlayingNow(page, query)
                    .flatMap { films ->
                        cache.retainFilms(
                            films ?: listOf()
                        )
                    }
            } else
                cache.getPlayingNow()
        }


    override fun getFilmDetails(filmId: Int): Single<FilmDetail> {
        return networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                cloud.getFilmDetails(filmId)
                    .flatMap { film ->
                        cache.retainFilmDetail(film)
                    }
            } else
                cache.getFilmDetails(filmId)
        }
    }

}