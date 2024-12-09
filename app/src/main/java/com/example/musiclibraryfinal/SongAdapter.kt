package com.example.musiclibraryfinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(val context: FragmentActivity?, var playlist: List<Song>): RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.song_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = playlist[position]
        holder.tvTitle.text = song.title
        holder.tvArtist.text = song.artist
        holder.bind(song)
    }

    override fun getItemCount(): Int {
        return playlist.size
    }

    fun updateSongs(newSongs: List<Song>) {
        playlist = newSongs
        notifyDataSetChanged()
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
