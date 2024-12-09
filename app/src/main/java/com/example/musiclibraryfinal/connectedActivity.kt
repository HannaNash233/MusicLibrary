package com.example.musiclibraryfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class connectedActivity : AppCompatActivity() {
    private lateinit var songAdapter: SongAdapter
    private lateinit var songDatabaseHelper: SongDatabaseHelper
    private lateinit var songRecycler: RecyclerView
    private var songs = mutableListOf<Song>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connected)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("Debug", "In on create")
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //songRecycler = findViewById(R.id.rvSongs)
        //val songs = createSongs()
        //val rvSongs = findViewById<RecyclerView>(R.id.rvSongs)

        //rvSongs.adapter = SongAdapter(this, songs)
        //rvSongs.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("Debug", "In the menu")
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            R.id.menuMain -> {
                Toast.makeText(this, "Main Menu clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, connectedActivity::class.java)
                startActivity(intent)
            }
            R.id.menuAdd -> {
                Toast.makeText(this, "Add Song clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, addSongActivity::class.java)
                startActivity(intent)
            }
            R.id.menuDelete -> {
                Toast.makeText(this, "Delete Song clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, deleteSongActivity::class.java)
                startActivity(intent)
            }
            R.id.menuLogout -> {
                Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

    private fun createSongs():List<Song> {
        val songs = mutableListOf<Song>()
        for (i in 1..50) {
            songs.add(Song("Song $i", "Artist $i", "Genre: $i", "Year: $i".toInt()))
        }
        return songs
    }

}