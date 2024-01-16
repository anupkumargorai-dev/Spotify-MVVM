package com.example.spotifyeminenceinnovationtask.album.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackData
import com.example.spotifyeminenceinnovationtask.album.models.AlbumsResponse
import com.example.spotifyeminenceinnovationtask.utils.Constants

@Dao
interface AlbumDao {
    @Query("SELECT * FROM ${Constants.AppConstants.Album_table_name}")
    suspend fun getAlbums() : TrackData?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(albums : TrackData)
}