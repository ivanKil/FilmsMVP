package com.lessons.filmsmvp.presentation.filmlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import com.lessons.filmsmvp.R
import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.data.film.FilmsRepository
import com.lessons.filmsmvp.databinding.FilmsLentaBinding
import com.lessons.filmsmvp.presentation.filmlist.adapter.FilmAdapter
import com.lessons.filmsmvp.presentation.filmlist.adapter.GlideImageLoader
import com.lessons.mvp.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class FilmsFragment : AbsFragment(R.layout.films_lenta), FilmsView, FilmAdapter.Delegate {

    companion object {
        fun newInstance(): Fragment = FilmsFragment()
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var filmsRepository: FilmsRepository

    private val presenter: FilmsPresenter by moxyPresenter {
        FilmsPresenter(filmsRepository, router)
    }

    private var vb: FilmsLentaBinding? = null
    private val repoAdapter = FilmAdapter(delegate = this, GlideImageLoader())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        vb = FilmsLentaBinding.inflate(inflater, container, false)
        vb?.listLayot?.adapter = repoAdapter
        return vb!!.root
    }

    override fun showRepos(users: List<Film>) {
        repoAdapter.submitList(users)
        repoAdapter.notifyDataSetChanged()
    }

    override fun setError(er: Throwable) {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onUserPicked(filmId: Int) {
        presenter.displayFilmInfo(filmId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}

