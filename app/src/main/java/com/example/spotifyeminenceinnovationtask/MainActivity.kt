package com.example.spotifyeminenceinnovationtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyeminenceinnovationtask.album.adapter.AlbumTrackAdapter
import com.example.spotifyeminenceinnovationtask.album.local.dao.AlbumDao
import com.example.spotifyeminenceinnovationtask.album.models.AudioTracks
import com.example.spotifyeminenceinnovationtask.album.viewmodel.SpotifyViewModel
import com.example.spotifyeminenceinnovationtask.databinding.ActivityMainBinding
import com.example.spotifyeminenceinnovationtask.utils.Constants
import com.example.spotifyeminenceinnovationtask.utils.NetworkResult
import com.example.spotifyeminenceinnovationtask.utils.isInternetAvailable
import com.example.spotifyeminenceinnovationtask.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: SpotifyViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var albumAdapter: AlbumTrackAdapter? = null

    @Inject
    lateinit var albumDao: AlbumDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkInternetConnectionAndLoadData()
    }

    private fun checkInternetConnectionAndLoadData() {
        if (this@MainActivity.isInternetAvailable()) {
            initObserver()
            viewModel.getAlbumTracks(Constants.AppConstants.Static_Album_id)

        } else {
            this@MainActivity.showToast("Loading Data from Room DataBase.....")
            CoroutineScope(Dispatchers.Main).launch {
                val trackData = albumDao.getAlbums()
                val trackDataItem = trackData?.audioList?.items
                if (trackDataItem.isNullOrEmpty().not()) {
                    val listOfAudioTrack = ArrayList<AudioTracks>()
                    var serialNumber = 0
                    trackDataItem?.forEach { track ->
                        listOfAudioTrack.add(
                            AudioTracks(
                                artists = track.artists?.artists,
                                name = track.name,
                                durationMS = track.durationMS,
                                previewUrl = track.previewUrl,
                                serialNumber = serialNumber++
                            )
                        )
                    }
                    albumAdapter = AlbumTrackAdapter(tracks = listOfAudioTrack)
                    albumAdapter?.let { initRv(albumAdapter = it) }
                } else {
                    this@MainActivity.showToast("No Data to show Connect to Internet")
                }
            }
        }
    }

    private fun initObserver() {

        viewModel.albumData.observe(this) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    this@MainActivity.showToast("Loading Data from Internet.....")

                }

                is NetworkResult.Success -> {
                    albumAdapter = result.data?.get(0)?.tracks?.items?.let { AlbumTrackAdapter(it) }
                    albumAdapter?.let { initRv(albumAdapter = it) }
                }

                is NetworkResult.Error -> {
                    this@MainActivity.showToast("Something went Wrong....")

                }
            }
        }
    }

    private fun initRv(albumAdapter: AlbumTrackAdapter) {
        binding.rvTracks.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = albumAdapter
        }
    }
}