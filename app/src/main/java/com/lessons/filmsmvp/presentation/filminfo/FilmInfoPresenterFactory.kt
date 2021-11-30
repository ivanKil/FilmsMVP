package com.lessons.filmsmvp.presentation.filminfo

import dagger.assisted.AssistedFactory

@AssistedFactory
interface FilmInfoPresenterFactory {
    fun create(filmId: Int): FilmInfoPresenter
}