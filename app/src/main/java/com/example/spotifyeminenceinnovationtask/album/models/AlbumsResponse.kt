package com.example.spotifyeminenceinnovationtask.album.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spotifyeminenceinnovationtask.utils.Constants
import com.google.gson.annotations.SerializedName

data class AlbumsResponse(
    @SerializedName("albums")
    val albums: List<Albums>?
)

data class Albums(
    @SerializedName("artists")
    val artists: List<Artists>?,
    @SerializedName("tracks")
    val tracks: AlbumTracks
)

data class Artists(
    @SerializedName("external_urls")
    val externalUrls: SpotifyUrl?,
    val id: String?,
    val name: String?,
    val type: String?,
    val uri: String?
)

data class SpotifyUrl(
    @SerializedName("spotify")
    val spotify: String?
)

data class AlbumTracks(
    @SerializedName("items")
    val items: List<AudioTracks>?
)

data class AudioTracks(
    var serialNumber : Int?,
    @SerializedName("artists")
    val artists: List<Artists>?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("duration_ms")
    val durationMS: String?,
    @SerializedName("preview_url")
    val previewUrl: String?,
)
