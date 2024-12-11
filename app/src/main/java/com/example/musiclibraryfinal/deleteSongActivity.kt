package com.example.musiclibraryfinal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class deleteSongActivity : AppCompatActivity() {
    // will add the database functions for this after you create the code - Hanna
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_song)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val songNameEditText: EditText = findViewById(R.id.songNameEditText)
        val deleteButton: Button = findViewById(R.id.deleteBTN)

        deleteButton.setOnClickListener {
            val songName = songNameEditText.text.toString()

            if(songName.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
            else {
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("Songs")

                val userRef = myRef.child(songName)

                userRef.removeValue()
                    .addOnSuccessListener {
                    Toast.makeText(this, "User successfully deleted.", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener() {
                        Toast.makeText(this, "Error deleting user", Toast.LENGTH_SHORT).show()
                    }
            }
        }
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