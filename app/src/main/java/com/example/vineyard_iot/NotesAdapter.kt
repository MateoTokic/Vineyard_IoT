package com.example.vineyard_iot

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore


 class NotesAdapter(private val noteList:ArrayList<Note>) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        var newNote = noteList[position]
        holder.bind(newNote)
    }
    override fun getItemCount(): Int {
        return noteList.size
    }
    class ViewHolder constructor(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private val noteTitle = itemview.findViewById<TextView>(R.id.noteTitle)
        fun bind(note: Note){
            noteTitle.text = note.Title
        }
    }
    fun addNote(title: String, position: Int) {
        var noteInfo = Note(title)
        if(noteList.size >= position) {
            noteList.add(position, noteInfo)
            notifyItemInserted(position)
        }
    }
    fun clearAll(){
        noteList.clear()
        notifyDataSetChanged()
    }
 }