package com.example.cloud_ass1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cloud_ass1.adapters.NoteAdapter
import com.example.cloud_ass1.models.Note
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var list = ArrayList<Note>()

        val myAdapter = NoteAdapter(list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        val db = Firebase.firestore
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list.add(
                        Note(
                            document.getString("name"),
                            document.getString("description"),
                            document.getString("letters")
                        )
                    )
                    Log.e("success", "${document.id} => ${document.data}")
                }
                myAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.e("error", "Error getting documents.", exception)
            }
        if(list.isEmpty()){
            textViewNoData.visibility = View.VISIBLE
        }else{
            textViewNoData.visibility = View.GONE
        }
        floatingActionButton.setOnClickListener {
            val i = Intent(applicationContext, MainActivity2::class.java)
            startActivity(i)
        }

    }
}