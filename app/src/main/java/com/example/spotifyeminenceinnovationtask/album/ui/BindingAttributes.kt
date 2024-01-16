package com.example.spotifyeminenceinnovationtask.album.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.spotifyeminenceinnovationtask.album.models.Artists

object BindingAttributes {
    @JvmStatic
    @BindingAdapter("concatenateArtistNames")
    fun TextView.concatenateArtistNames(artists: List<Artists>?) {
        text = artists?.joinToString(separator = ", ", prefix = "Artist: ") { it.name.orEmpty() } ?: ""
    }
}