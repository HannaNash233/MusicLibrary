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
 * Use the [deleteSongFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class deleteSongFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_delete_song, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backButton: Button = view.findViewById(R.id.backButton)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_deleteSongFragment_to_mainFragment)
        }

        val deleteBTN = view.findViewById<Button>(R.id.deleteBTN)
        val songTitleEdit = view.findViewById<EditText>(R.id.songTitleEdit)

        deleteBTN.setOnClickListener {
            val songTitle = songTitleEdit.text.toString()

            if (songTitle.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
            else {
                // Initialize Firebase Database reference
                val database = FirebaseDatabase.getInstance() //.reference

                val myRef =
                    database.getReference("users")

                val userRef = myRef.child(songTitle)

                userRef.removeValue()
                    .addOnSuccessListener {
                        // Song deleted successfully
                        Toast.makeText(this, "Song successfully deleted.", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        // Handle failure
                        Toast.makeText(this, "Error deleting song", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment deleteSongFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            deleteSongFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}