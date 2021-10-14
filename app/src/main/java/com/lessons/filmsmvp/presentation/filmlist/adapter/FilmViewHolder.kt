package com.lessons.filmsmvp.presentation.filmlist.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lessons.filmsmvp.data.di.modules.POSTER_BASE_URL
import com.lessons.filmsmvp.data.film.Film
import com.lessons.filmsmvp.databinding.ItemFilmBinding


class FilmViewHolder(view: View, val imageLoader: IImageLoader<ImageView>) :
    RecyclerView.ViewHolder(view) {

    private val viewBinding: ItemFilmBinding by viewBinding()

    fun bind(film: Film, delegate: FilmAdapter.Delegate?) {
        with(viewBinding) {
            title.text = film.name
            cardFilm.setOnClickListener { delegate?.onUserPicked(film.id) }
            imageLoader.loadInto(POSTER_BASE_URL + film.poster ?: "", poster)
        }
    }

}