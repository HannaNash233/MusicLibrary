package com.example.musiclibraryfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.musiclibraryfinal.SongsAdapter.ViewHolder

class SongAdapter(val context: Context, val playlist: List<Song>): RecyclerView.Adapter<SongAdapter.ViewHolder>() {
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

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)

        fun bind(song: Song){
            tvTitle.text = song.title
            tvArtist.text = song.artist
        }
    }
}
