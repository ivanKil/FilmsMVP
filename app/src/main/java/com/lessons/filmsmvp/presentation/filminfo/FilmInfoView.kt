package com.lessons.filmsmvp.presentation.filminfo

import com.lessons.filmsmvp.data.film.FilmDetail
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface FilmInfoView : MvpView {
    fun showFilmInfo(film: FilmDetail)
    fun setError(er: Throwable)
}