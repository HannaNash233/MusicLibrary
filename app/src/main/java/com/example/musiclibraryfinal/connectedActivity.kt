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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class connectedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        private lateinit var songAdapter: SongAdapter
        private lateinit var songDatabaseHelper: SongDatabaseHelper
        private lateinit var songRecycler: RecyclerView
        private var songs = mutableListOf<Song>()

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

        songRecycler = findViewById(R.id.rvSongs)
        val playlist = createSongs()

        Log.d("Debug", "Test1")
        songDatabaseHelper = SongDatabaseHelper.getInstance(this)
        Log.d("Debug", "Test2")
        songAdapter = SongAdapter(this, songs)
        // use get activity
        songRecycler.layoutManager = LinearLayoutManager(this)
        songRecycler.adapter = songAdapter

        songs = songDatabaseHelper.getAllItems().toMutableList()
        songAdapter.updateSongs(songs)

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
