package com.example.musiclibraryfinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addSongFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addSongFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var database: FirebaseDatabase
    private lateinit var addBtn: Button
    private lateinit var songTitleEdit: EditText
    private lateinit var artistEdit: EditText
    private lateinit var genreEdit: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()
        val ref = database.getReference("playlist")
        addBtn = view.findViewById<Button>(R.id.addBTN)
        songTitleEdit = view.findViewById<EditText>(R.id.songTitleEdit)
        artistEdit = view.findViewById<EditText>(R.id.artistEdit)
        genreEdit = view.findViewById<EditText>(R.id.genreEdit)
        val yearEdit = view.findViewById<EditText>(R.id.yearEdit)




        addBtn.setOnClickListener {
            val songTitle = songTitleEdit.text.toString()
            val artistName = artistEdit.text.toString()
            val genre = genreEdit.text.toString()
            val year = yearEdit.text.toString()

            if (songTitle.isEmpty() || artistName.isEmpty() || genre.isEmpty() || year.isEmpty()){
                Toast.makeText(activity, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            } else {
                val song = Song(songTitle, artistName, genre, year.toInt())

                ref.child(songTitle).setValue(song).addOnSuccessListener {
                    Toast.makeText(activity, "Song officially added", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(activity, "Failed to add song", Toast.LENGTH_SHORT).show()
                }
            }
            findNavController().navigate(R.id.action_addSongFragment_to_mainFragment)

        }


        val backButton: Button = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addSongFragment_to_mainFragment)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addSongFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addSongFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}