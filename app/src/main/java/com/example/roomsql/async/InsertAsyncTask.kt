package com.example.roomsql.async

import android.os.AsyncTask
import android.util.Log
import com.example.roomsql.models.Note
import com.example.roomsql.persistence.NoteDao

class InsertAsyncTask(mNoteDao: NoteDao) : AsyncTask<Note, Void, Void>() {

    private val mNoteDao: NoteDao? = null

    init {
        mNoteDao
    }

    override fun doInBackground(vararg notes: Note?): Void? {
        Log.d("AsyncTask", Thread.currentThread().name)
        mNoteDao?.insertNotes(notes)
        return null
    }

}