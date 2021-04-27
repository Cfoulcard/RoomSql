package com.example.roomsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.roomsql.models.Note

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "NotesListActivity"

        val note = Note("Title", "Johnny", "TBA")
       // val note = Note("some title", "some content for the note", null)

        Log.d(TAG, "onCreate: my notes: $note")

    }
}