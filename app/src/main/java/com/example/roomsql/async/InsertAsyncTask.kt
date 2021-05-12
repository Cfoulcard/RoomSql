package com.example.roomsql.async

import android.os.AsyncTask
import android.util.Log
import com.example.roomsql.models.Note
import com.example.roomsql.persistence.NoteDao

class InsertAsyncTask(dao: NoteDao) : AsyncTask<Note, Void, Void>() {

    private var mNoteDao: NoteDao? = dao


    init {
        mNoteDao
    }

    override fun doInBackground(vararg notes: Note?): Void? {
        Log.d("AsyncTask", Thread.currentThread().name)
        mNoteDao?.insertNotes(notes)
        return null
    }

}