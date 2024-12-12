package com.example.musiclibraryfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.widget.Toolbar
import com.google.firebase.database.FirebaseDatabase

class searchSongActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search_song)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnsearch = findViewById<Button>(R.id.btn_search)
        val et_title_search = findViewById<EditText>(R.id.et_title_search)
        val tv_dataTitle = findViewById<TextView>(R.id.tv_dataTitle)
        val tv_dataArtist = findViewById<TextView>(R.id.tv_dataArtist)
        val tv_dataGenre = findViewById<TextView>(R.id.tv_dataGenre)
        val tv_dataYear = findViewById<TextView>(R.id.tv_dataYear)


        btnsearch.setOnClickListener {
            val songName = et_title_search.text.toString()
            if (songName.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Songs")
                myRef.child(songName).get().addOnSuccessListener { snapshot ->
                    if (snapshot.exists()) {
                        val song = snapshot.getValue(Song::class.java)
                        if (song != null) {
                            Toast.makeText(
                                this,
                                "Title: ${song.title}, Artist: ${song.artist}, Genre: ${song.genre}, Year: ${song.pubYear}",
                                Toast.LENGTH_SHORT
                            ).show()
                            tv_dataTitle.text = song.title
                            tv_dataArtist.text = song.artist
                            tv_dataGenre.text = song.genre
                            tv_dataYear.text = song.pubYear.toString()
                        }
                        } else {
                            Toast.makeText(this, "no such song", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, "Error reading data", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("Debug", "In the menu")
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            R.id.menuMain -> {
                Toast.makeText(this, "Playlist clicked", Toast.LENGTH_SHORT).show()
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
            R.id.menuSearch -> {
                Toast.makeText(this, "Search for Song clicked", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, searchSongActivity::class.java)
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
}