package com.example.musiclibraryfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongsAdapter(private val context: Context, private val songs: List<Song>): RecyclerView.Adapter<SongsAdapter.ViewHolder>(){
    // creating a new view: expensive
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.song_item,parent,false)
        return ViewHolder(view)
    }
    //bind the data at position into the viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song, this)


    }
    //count how many items are in the data set
    override fun getItemCount():Int{
        return songs.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)

        fun bind(song: Song, listener: SongsAdapter) {
            //bind the data in the contact into the views
            tvTitle.text = song.title
            tvArtist.text = song.artist
            // Set click listener

        }
    }
}