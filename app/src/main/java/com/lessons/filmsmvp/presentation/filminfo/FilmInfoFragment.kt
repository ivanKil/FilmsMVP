package com.lessons.filmsmvp.presentation.filminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.lessons.filmsmvp.R
import com.lessons.filmsmvp.data.di.modules.POSTER_BASE_URL
import com.lessons.filmsmvp.data.film.FilmDetail
import com.lessons.filmsmvp.databinding.FilmInfoBinding
import com.lessons.mvp.presentation.abs.AbsFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import javax.inject.Inject

const val EXT_FILM_ID = "EXT_FILM_ID"

class FilmInfoFragment : AbsFragment(R.layout.film_info), FilmInfoView {
    private val format = SimpleDateFormat("dd.MM.yyyy")

    companion object {
        fun newInstance(filmId: Int): Fragment = FilmInfoFragment().apply {
            arguments = bundleOf(Pair(EXT_FILM_ID, filmId))
        }
    }

    @Inject
    lateinit var filmInfoPresenterFactory: FilmInfoPresenterFactory

    private val presenter: FilmInfoPresenter by moxyPresenter {
        filmInfoPresenterFactory.create(requireArguments().getInt(EXT_FILM_ID))
    }

    //private val vb: RepoInfoBinding by viewBinding() // тоже самое, не работает, валится ошибка
    //Fragment doesn't have view associated with it or the view has been destroyed

    private var vb: FilmInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        vb = FilmInfoBinding.inflate(inflater, container, false)
        return vb!!.root
    }

    override fun showFilmInfo(film: FilmDetail) {
        vb?.apply {
            infoName.text = film.name
            infoBudget.text =
                String.format(
                    getResources().getString(R.string.budget),
                    if (film.budget == null || film.budget == 0) resources.getString(R.string.dont_know)
                    else film.budget.toString() + "$"
                )
            infoGenge.text = film.genres?.map { it.name }?.joinToString()
            infoOverview.text = film.overview
            Glide.with(infoPoster).load(POSTER_BASE_URL + film.poster).centerCrop().into(infoPoster)
            infoReleseDate.text = String.format(
                getResources().getString(R.string.release_date),
                format.format(film.releaseDate)
            )
            infoRunTime.text =
                String.format(getResources().getString(R.string.run_time), film.runTime.toString())
            infoVote.text = film.voteAverage.toString()
            infoOverview.text = film.overview
        }
    }

    override fun setError(er: Throwable) {
        Toast.makeText(
            requireActivity(), requireContext().getString(R.string.err),
            Toast.LENGTH_LONG
        ).show();
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}

