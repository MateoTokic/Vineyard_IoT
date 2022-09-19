package com.example.vineyard_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

import com.google.firebase.internal.InternalTokenProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NotesActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    private lateinit var deleteNote: Button
    private lateinit var addNote: Button
    private lateinit var inputNote: EditText
    private lateinit var noteAdapter: NotesAdapter
    private lateinit var rv: RecyclerView
    private lateinit var noteArrayList: ArrayList<Note>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        mAuth = FirebaseAuth.getInstance()

        rv = findViewById(R.id.rvNotes)
        rv.layoutManager = LinearLayoutManager(this)
        rv.setHasFixedSize(true)

        noteArrayList = arrayListOf()
        noteAdapter = NotesAdapter(noteArrayList)
        rv.adapter = noteAdapter

        deleteNote = findViewById(R.id.deleteNote)
        addNote = findViewById(R.id.addNote)
        inputNote = findViewById(R.id.inputNote)

        addNote?.setOnClickListener {
            val noteTitle = inputNote.text.toString()
            noteAdapter.addNote(noteTitle, noteArrayList.size)
        }
        deleteNote?.setOnClickListener {
            noteAdapter.clearAll()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this@NotesActivity, LogInActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }
        if(item.itemId == R.id.select){
            val intent2 = Intent(this@NotesActivity, SelectActivity::class.java)
            startActivity(intent2)
            return true
        }

        return true
    }
}