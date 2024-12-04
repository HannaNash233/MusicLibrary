package com.example.musiclibraryfinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

//data class Music(val title: String, val artist: String, val genre: String)
class MainActivity : AppCompatActivity() {
    private lateinit var playlistRecyclerView: RecyclerView
    private var playlist = mutableListOf<Song>()
    private lateinit var login: Button
    private lateinit var signup: Button
    private lateinit var email: TextInputEditText
    private lateinit var pass: TextInputEditText
    private lateinit var passConfirm: TextInputEditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // signing up the user
        email = findViewById<TextInputEditText>(R.id.tiet_email)
        pass = findViewById<TextInputEditText>(R.id.tiet_pwd)
        passConfirm = findViewById<TextInputEditText>(R.id.tiet_pwdConfirm)



        signup = findViewById<Button>(R.id.signUpBTN)
        auth = FirebaseAuth.getInstance()

        signup.setOnClickListener{
            val emailText = email.text.toString()
            val passText = pass.text.toString()
            val passConfirmText = passConfirm.text.toString()

            if (emailText.isEmpty() || passText.isEmpty() || passConfirmText.isEmpty()){
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }

            if (passText != passConfirmText){
                passConfirm.error = "Passwords do not match"
            } else {
                passConfirm.error = null

                auth.createUserWithEmailAndPassword(emailText, passText).addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Sign up successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, connectedActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        login = findViewById(R.id.loginBTN)
        login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }









    }


}