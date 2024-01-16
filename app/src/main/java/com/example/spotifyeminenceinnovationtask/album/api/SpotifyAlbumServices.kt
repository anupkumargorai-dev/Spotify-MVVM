package com.example.spotifyeminenceinnovationtask.album.api

import com.example.spotifyeminenceinnovationtask.album.models.AlbumsResponse
import com.example.spotifyeminenceinnovationtask.utils.Constants
import com.example.spotifyeminenceinnovationtask.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyAlbumServices {
    @GET(Constants.ApiEndPoints.Albums)
    suspend fun getAlbumsTracks(@Query("ids") albumId : String) : Response<AlbumsResponse>
}