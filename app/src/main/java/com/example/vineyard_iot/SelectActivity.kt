package com.example.vineyard_iot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class SelectActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var status : CardView
    private lateinit var notes : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        mAuth = FirebaseAuth.getInstance()
        status = findViewById(R.id.status)
        notes = findViewById(R.id.notes)

        status.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        notes.setOnClickListener{
            var intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout){
            mAuth.signOut()
            val intent = Intent(this@SelectActivity, LogInActivity::class.java)
            finish()
            startActivity(intent)
            return true
        }
        return true
    }
}