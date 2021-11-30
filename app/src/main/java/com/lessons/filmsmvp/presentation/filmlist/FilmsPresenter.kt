package com.lessons.filmsmvp.presentation.filmlist

import com.github.terrakok.cicerone.Router
import com.lessons.filmsmvp.data.film.FilmsRepository
import com.lessons.filmsmvp.presentation.FilmInfoScreen
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class FilmsPresenter(
    private val filmsRepository: FilmsRepository,
    private val router: Router
) : MvpPresenter<FilmsView>() {
    private var disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        loadData()
    }

    private fun loadData() {
        filmsRepository.getPlayingNow(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showRepos(it) },
                viewState::setError
            )
            .addTo(disposables)
    }

    fun displayFilmInfo(filmId: Int) {
        router.navigateTo(FilmInfoScreen(filmId))
    }

    override fun onDestroy() {
        disposables.clear()
    }
}