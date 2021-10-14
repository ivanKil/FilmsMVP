package com.lessons.filmsmvp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.lessons.filmsmvp.presentation.filminfo.FilmInfoFragment
import com.lessons.filmsmvp.presentation.filmlist.FilmsFragment


object FilmsScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment = FilmsFragment.newInstance()
}

class FilmInfoScreen(private var filmId: Int) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        FilmInfoFragment.newInstance(filmId)
}


