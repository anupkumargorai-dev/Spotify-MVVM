package com.example.spotifyeminenceinnovationtask.album.repository

import com.example.spotifyeminenceinnovationtask.album.api.SpotifyAlbumServices
import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackData
import com.example.spotifyeminenceinnovationtask.album.models.AlbumsResponse
import retrofit2.Response
import javax.inject.Inject

class SpotifyAlbumRepositoryImp @Inject constructor(
    private val spotifyAlbumServices : SpotifyAlbumServices
) : SpotifyAlbumRepository {
    override suspend fun getAlbumTracks(albumId: String): Response<AlbumsResponse> {
        return spotifyAlbumServices.getAlbumsTracks(albumId)
    }

    override suspend fun getAlbumsTracksFromDB(albumDao: AlbumDao): TrackData? {
        return albumDao.getAlbums()
    }

    override suspend fun insertAlbumTrackToDB(albumsResponse: TrackData, albumDao: AlbumDao) {
        albumDao.insertAlbums(albumsResponse)
    }

}