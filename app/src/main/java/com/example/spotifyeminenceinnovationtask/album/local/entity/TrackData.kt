package com.example.spotifyeminenceinnovationtask.album.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.spotifyeminenceinnovationtask.album.models.Artists
import com.example.spotifyeminenceinnovationtask.utils.Constants

@Entity(tableName = Constants.AppConstants.Album_table_name)
data class TrackData(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val audioList : AudioTrackList
)

data class ArtistList(
    val artists: List<Artists>?
)

data class AudioTrackList(
    val items: List<TrackDataItem>?
)

data class TrackDataItem(
    val serialNumber: Int,
    val name: String?,
    val durationMS: String?,
    val previewUrl: String?,
    val artists: ArtistList?
)
