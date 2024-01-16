package com.example.spotifyeminenceinnovationtask.album.di

import com.example.spotifyeminenceinnovationtask.album.api.SpotifyAlbumServices
import com.example.spotifyeminenceinnovationtask.album.repository.SpotifyAlbumRepository
import com.example.spotifyeminenceinnovationtask.album.repository.SpotifyAlbumRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSpotifyAlbumServices(retrofit: Retrofit) : SpotifyAlbumServices {
        return retrofit.create(SpotifyAlbumServices::class.java)
    }

    @Provides
    @Singleton
    fun provideSpotifyAlbumRepository(spotifyAlbumRepositoryImp: SpotifyAlbumRepositoryImp) : SpotifyAlbumRepository{
        return spotifyAlbumRepositoryImp
    }

}