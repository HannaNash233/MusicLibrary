package com.example.musiclibraryfinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongsAdapter(val context: Context, val songs: List<Song>, private val listener: OnItemClickListener): RecyclerView.Adapter<SongsAdapter.ViewHolder>(){
    interface OnItemClickListener {
        fun onItemClick(song: Song)
    }
    // creating a new view: expensive
    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.song_item,parent,false)
        return ViewHolder(view)
    }
    //bind the data at position into the viewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song: Song = songs[position]
        holder.bind(song, listener)
    }
    //count how many items are in the data set
    override fun getItemCount():Int{
        return songs.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvArtist = itemView.findViewById<TextView>(R.id.tvArtist)

        fun bind(song: Song, listener: OnItemClickListener) {
            //bind the data in the contact into the views
            tvTitle.text = song.title
            tvArtist.text = song.artist
            // Set click listener
            itemView.setOnClickListener {
                listener.onItemClick(song) // Notify the listener of the click event
            }
        }
    }
}