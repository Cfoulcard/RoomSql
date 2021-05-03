package com.example

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.roomsql.R
import com.example.roomsql.models.Note

class NoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dummy)

        // Parcelable Bundle Data from the Note class
        if (intent.hasExtra("selected note")) {
            val note = intent.getParcelableExtra<Note>("selected note")
            Log.d("Parcel working", "oncreate $note")
        }
    }
}