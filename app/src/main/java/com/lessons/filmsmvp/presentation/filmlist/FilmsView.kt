package com.lessons.filmsmvp.presentation.filmlist

import com.lessons.filmsmvp.data.film.Film
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface FilmsView : MvpView {
    fun showRepos(films: List<Film>)
    fun setError(er: Throwable)
}