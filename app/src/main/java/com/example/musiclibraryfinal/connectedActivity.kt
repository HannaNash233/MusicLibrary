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