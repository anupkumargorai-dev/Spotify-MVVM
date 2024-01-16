package com.example.spotifyeminenceinnovationtask.album.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.local.entity.ArtistList
import com.example.spotifyeminenceinnovationtask.album.local.entity.AudioTrackList
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackData
import com.example.spotifyeminenceinnovationtask.album.local.entity.TrackDataItem
import com.example.spotifyeminenceinnovationtask.album.models.Albums
import com.example.spotifyeminenceinnovationtask.album.repository.SpotifyAlbumRepository
import com.example.spotifyeminenceinnovationtask.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpotifyViewModel @Inject constructor(
    private val spotifyAlbumRepository: SpotifyAlbumRepository,
    private val albumDao: AlbumDao
) : ObservableViewModel() {

    private var _albumData = MutableLiveData<NetworkResult<List<Albums>>>()
    val albumData: LiveData<NetworkResult<List<Albums>>>
        get() = _albumData


    fun getAlbumTracks(albumId: String) {
        viewModelScope.launch {
            _albumData.postValue(NetworkResult.Loading())
            spotifyAlbumRepository.getAlbumTracks(albumId).let { response ->
                if (response.isSuccessful) {
                    response.body()?.albums.let {
                        response.body()
                            ?.let { albumResponse ->
                                val items: ArrayList<TrackDataItem> = ArrayList()
                                var serialNumber = 0
                                albumResponse.albums?.forEach { album ->
                                    album.tracks.items?.forEach { track ->
                                        val tractItem = TrackDataItem(
                                            name = track.name,
                                            durationMS = track.durationMS,
                                            previewUrl = track.previewUrl,
                                            artists = ArtistList(artists = track.artists),
                                            serialNumber = serialNumber++
                                        )
                                        items.add(tractItem)
                                    }
                                }
                                if (items.isNotEmpty()) spotifyAlbumRepository.insertAlbumTrackToDB(
                                    TrackData(audioList = AudioTrackList(items = items)),
                                    albumDao
                                )
                            }
                        _albumData.postValue(NetworkResult.Success(it!!))
                    }
                } else {
                    _albumData.postValue(NetworkResult.Error("Something Went Wrong.."))
                }
            }
        }
    }
}