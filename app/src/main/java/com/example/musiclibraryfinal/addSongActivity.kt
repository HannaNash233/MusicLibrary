package com.example.musiclibraryfinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class addSongActivity : AppCompatActivity() {
    private lateinit var addBTN: Button

    private lateinit var viewPlayBTN: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_song)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Songs")
        val titleAdd = findViewById<EditText>(R.id.titleAdd)
        val artistAdd = findViewById<EditText>(R.id.artistAdd)
        val genreAdd = findViewById<EditText>(R.id.genreAdd)
        val yearAdd = findViewById<EditText>(R.id.yearAdd)
        addBTN = findViewById(R.id.addBTN)

        addBTN.setOnClickListener {
            val title = titleAdd.text.toString()
            val artist = artistAdd.text.toString()
            val genre = genreAdd.text.toString()
            val year = yearAdd.text.toString()

            if (title.isEmpty() || artist.isEmpty() || genre.isEmpty() || year.isEmpty()){
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val song = Song(title, artist, genre, year.toInt())

                ref.child(title).setValue(song).addOnSuccessListener{
                    Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()
                } .addOnFailureListener{
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
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