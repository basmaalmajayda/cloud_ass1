package com.example.cloud_ass1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        addButton.setOnClickListener {
            var note = hashMapOf(
                "name" to NameEditText.text.toString(),
                "description" to DescriptionEditText.text.toString(),
                "letters" to LettersEditText.text.toString()
            )
            val db = Firebase.firestore
            db.collection("notes")
                .add(note)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                    val i = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                    //Toast message
                    Toast.makeText(this@MainActivity2, "There is something wrong", Toast.LENGTH_SHORT).show()
                }

        }
    }
}