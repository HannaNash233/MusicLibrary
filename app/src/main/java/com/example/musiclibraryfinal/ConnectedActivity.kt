package com.example.musiclibraryfinal

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class ConnectedActivity : AppCompatActivity() {
    //private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var addBtn: Button
    private lateinit var deleteBtn: Button
    private lateinit var songTitleEdit: EditText
    private lateinit var artistEdit: EditText
    private lateinit var genreEdit: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connected)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)
        //auth = FirebaseAuth.getInstance()
        // may need to change the code if we use the fragments
        database = FirebaseDatabase.getInstance()
        val ref = database.getReference("playlist")
        addBtn = findViewById<Button>(R.id.addBTN)
        songTitleEdit = findViewById<EditText>(R.id.songTitleEdit)
        artistEdit = findViewById<EditText>(R.id.artistEdit)
        genreEdit = findViewById<EditText>(R.id.genreEdit)



        addBtn.setOnClickListener {
            val songTitle = songTitleEdit.text.toString()
            val artistName = artistEdit.text.toString()
            val genre = genreEdit.text.toString()

            if(songTitle.isEmpty() || artistName.isEmpty() || genre.isEmpty()){
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val song = Song(songTitle, artistName, genre)

                ref.child(songTitle).setValue(song).addOnSuccessListener {
                    Toast.makeText(this, "Song saved in playlist!", Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener{
                        Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                    }
            }
           // add fragment code
        }


    }

    // i commented the tool bar but didn't delete completely in case we want to do it again - Hanna
    // also please look at the connected.xml and if it isn't the vision go for the change
    /*override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }*/

    /*override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            R.id.menuMain -> Toast.makeText(this, "Main Menu clicked", Toast.LENGTH_SHORT).show()
            R.id.menuAdd -> Toast.makeText(this, "Add Song clicked", Toast.LENGTH_SHORT).show()
            R.id.menuDelete -> Toast.makeText(this, "Delete Song clicked", Toast.LENGTH_SHORT).show()
            R.id.menuLogout -> Toast.makeText(this, "Logout clicked", Toast.LENGTH_SHORT).show()
        }
        return true
    }*/
}