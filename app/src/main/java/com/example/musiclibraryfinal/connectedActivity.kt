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
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class connectedActivity : AppCompatActivity() {
    private lateinit var songAdapter: SongAdapter
    //private lateinit var songDatabaseHelper: SongDatabaseHelper
    //private lateinit var songRecycler: RecyclerView
    private lateinit var reference: DatabaseReference
    private var playlist = mutableListOf<Song>()
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
        //val songRecycler = findViewById<RecyclerView>(R.id.rvSongs)


        //songRecycler.adapter = SongAdapter(this, songs)
        //songRecycler.layoutManager = LinearLayoutManager(this)
        initalizeRV()

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
    private fun initalizeRV(){
        val songRecycler = findViewById<RecyclerView>(R.id.rvSongs)
        songAdapter = SongAdapter(this, playlist)
        songRecycler.adapter = songAdapter
        songRecycler.layoutManager = LinearLayoutManager(this)

        reference = FirebaseDatabase.getInstance().getReference("Songs")

        reference.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val song = snapshot.getValue(Song::class.java)
                if (song != null){
                    playlist.add(song)
                    songAdapter.updatePlaylist(playlist)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val newSong = snapshot.getValue(Song::class.java)
                if (newSong != null){
                    val i = playlist.indexOfFirst{it.title == newSong.title}
                    if (i != -1){
                        playlist[i] = newSong
                        songAdapter.notifyItemChanged(i)
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
               val song = snapshot.getValue(Song::class.java)
                if (song != null) {
                    val i = playlist.indexOfFirst { it.title == song.title }
                    if (i != -1){
                        playlist.removeAt(i)
                        songAdapter.notifyItemRemoved(i)
                    }
                }
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                Log.d("Debug", "Moved")
                //nothing there
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Debug", "Cancelled")
                //nothing there
            }

        })
    }



}