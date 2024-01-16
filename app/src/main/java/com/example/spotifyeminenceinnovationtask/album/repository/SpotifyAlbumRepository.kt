package com.example.spotifyeminenceinnovationtask.album.repository

import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackData
import com.example.spotifyeminenceinnovationtask.album.models.AlbumsResponse
import retrofit2.Response

interface SpotifyAlbumRepository {

    suspend fun getAlbumTracks(albumId: String): Response<AlbumsResponse>

    suspend fun getAlbumsTracksFromDB(albumDao: AlbumDao): TrackData?

    suspend fun insertAlbumTrackToDB(albumsResponse: TrackData, albumDao: AlbumDao)
}