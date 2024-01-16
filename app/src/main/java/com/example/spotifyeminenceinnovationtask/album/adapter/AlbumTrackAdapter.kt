package com.example.spotifyeminenceinnovationtask.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyeminenceinnovationtask.album.models.AudioTracks
import com.example.spotifyeminenceinnovationtask.databinding.AlbumTrackItemsBinding

class AlbumTrackAdapter(private val tracks: List<AudioTracks>) : RecyclerView.Adapter<AlbumTrackViewHolder>() {

    private lateinit var binding: AlbumTrackItemsBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumTrackViewHolder {
        binding = AlbumTrackItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumTrackViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return tracks.count()
    }

    override fun onBindViewHolder(holder: AlbumTrackViewHolder, position: Int) {
        holder.bind(audioTracks = tracks[position], position)
    }
}

class AlbumTrackViewHolder(private val binding: AlbumTrackItemsBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(audioTracks: AudioTracks, position: Int){
        audioTracks.serialNumber = position + 1
        binding.trackData = audioTracks
    }
}