package com.example.spotifyeminenceinnovationtask.album.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.spotifyeminenceinnovationtask.album.local.converter.AppTypeConverters
import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackData
import com.example.spotifyeminenceinnovationtask.album.models.AlbumsResponse

@TypeConverters(value = [AppTypeConverters::class])
@Database(entities = [TrackData::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

}