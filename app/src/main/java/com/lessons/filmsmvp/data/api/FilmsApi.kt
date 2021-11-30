package com.lessons.filmsmvp.data.api

import com.lessons.filmsmvp.data.film.FilmDetail
import com.lessons.filmsmvp.data.film.FilmsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmsApi {

    @GET("movie/now_playing")
    fun getPlayingNow(
        @Query("page") page: Int,
        @Query("query") query: String? = null,
    ): Observable<FilmsResponse>

    @GET("movie/{movie_id}")
    fun getFilmDetails(
        @Path(value = "movie_id", encoded = true) filmId: Int,
        @Query("append_to_response") append_to_response: String = "credits"
    ): Observable<FilmDetail>
}