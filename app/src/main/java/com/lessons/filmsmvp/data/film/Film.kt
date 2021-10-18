package com.lessons.filmsmvp.data.film

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity()
data class Film(
    @PrimaryKey
    val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("poster_path") val poster: String?,
) : Parcelable

@Parcelize
data class FilmsResponse(
    val page: Int?,
    val results: List<Film>?,
    val total_pages: Int?,
    val total_results: Int?
) : Parcelable

@Entity
@Parcelize
data class FilmDetail(
    @PrimaryKey val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("poster_path") val poster: String?,
    @SerializedName("vote_average") val voteAverage: Double = 0.0,
    @SerializedName("release_date") val releaseDate: Date,
    @SerializedName("genres") val genres: List<Genre>?,
    @SerializedName("runtime") val runTime: Int?,
    val budget: Int?,
    val overview: String? = null,
    var saveDate: Date?,
) : Parcelable

@Parcelize
data class Genre(val id: Int, val name: String) : Parcelable