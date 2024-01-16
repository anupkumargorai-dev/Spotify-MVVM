package com.example.spotifyeminenceinnovationtask.album.local.converter

import androidx.room.TypeConverter
import com.example.spotifyeminenceinnovationtask.album.local.entity.ArtistList
import com.example.spotifyeminenceinnovationtask.album.local.entity.AudioTrackList
import com.google.gson.Gson

class AppTypeConverters {
    @TypeConverter
    fun fromClothingToJSON(artistsList: ArtistList): String {
        return Gson().toJson(artistsList)
    }
    @TypeConverter
    fun fromJSONToClothing(json: String): ArtistList {
        return Gson().fromJson(json,ArtistList::class.java)
    }@TypeConverter
    fun fromTrackListToJSON(audioTrackList: AudioTrackList): String {
        return Gson().toJson(audioTrackList)
    }
    @TypeConverter
    fun fromJSONToTrackList(jsonString: String): AudioTrackList {
        return Gson().fromJson(jsonString,AudioTrackList::class.java)
    }
}