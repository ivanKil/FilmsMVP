package com.lessons.filmsmvp.presentation.filminfo

import com.lessons.filmsmvp.data.film.FilmsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FilmInfoPresenter @AssistedInject constructor(
    @Assisted private val filmId: Int,
    private val filmsRepository: FilmsRepository
) : MvpPresenter<FilmInfoView>() {
    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadData()
    }

    private fun loadData() {
        filmsRepository.getFilmDetails(filmId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                viewState::showFilmInfo,
                viewState::setError
            )
            .addTo(disposables)
    }

    override fun onDestroy() {
        disposables.clear()
    }
}