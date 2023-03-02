package com.example.cloud_ass1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cloud_ass1.R
import com.example.cloud_ass1.models.Note
import kotlinx.android.synthetic.main.activity_main2.view.textView
import kotlinx.android.synthetic.main.card.view.*

class NoteAdapter(val list: ArrayList<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(){

    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView) {
        val name = itemView.NameTextView
        val description = itemView.DescriptionTextView
        val letters = itemView.LettersTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        holder.name.text = current.myName
        holder.description.text = current.myDescription
        holder.letters.text = current.myLetters
    }

    override fun getItemCount(): Int {
        return list.size
    }

}