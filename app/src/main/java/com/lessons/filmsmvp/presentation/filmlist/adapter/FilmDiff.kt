package com.lessons.filmsmvp.presentation.filmlist.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.lessons.filmsmvp.data.film.Film

object FilmDiff : DiffUtil.ItemCallback<Film>() {

    private val payload = Any()

    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Film, newItem: Film) = payload

}

