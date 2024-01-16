package com.example.spotifyeminenceinnovationtask.album.local.di

import android.content.Context
import androidx.room.Room
import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.local.AppDataBase
import com.example.spotifyeminenceinnovationtask.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext,
            AppDataBase::class.java,
            Constants.AppConstants.AppDataBase
        ).build()
    }

    @Provides
    fun provideAlbumDao(appDatabase: AppDataBase): AlbumDao {
        return appDatabase.albumDao()
    }
}