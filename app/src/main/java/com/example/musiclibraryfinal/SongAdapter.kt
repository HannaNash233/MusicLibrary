package com.example.musiclibraryfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(val context: Context, var playlist: List<Song>): RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song: Song = playlist[position]
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return playlist.size
    }

    fun updatePlaylist(newSong: List<Song>){
        playlist = newSong
        notifyDataSetChanged()
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)
        val tvGenre = itemView.findViewById<TextView>(R.id.tvGenre)
        val tvYear = itemView.findViewById<TextView>(R.id.tvYear)

        fun bind(song: Song){
            tvTitle.text = song.title
            tvArtist.text = song.artist
            tvGenre.text = song.genre
            tvYear.text = song.pubYear.toString()
        }
    }
}
