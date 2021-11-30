package com.lessons.filmsmvp.presentation.filmlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import com.lessons.filmsmvp.R
import com.lessons.filmsmvp.data.film.Film


class FilmAdapter(private val delegate: Delegate?, val imageLoader: IImageLoader<ImageView>) :
    ListAdapter<Film, FilmViewHolder>(FilmDiff) {

    interface Delegate {
        fun onUserPicked(filmId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder =
        FilmViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_film, parent, false), imageLoader
        )

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) =
        holder.bind(getItem(position), delegate)

}