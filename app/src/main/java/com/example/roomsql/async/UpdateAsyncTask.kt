package com.example.roomsql.async

import android.os.AsyncTask
import com.example.roomsql.models.Note
import com.example.roomsql.persistence.NoteDao

class UpdateAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note?, Void?, Void?>() {

    override fun doInBackground(vararg notes: Note?): Void? {
        mNoteDao.updateNotes(*notes)
        return null
    }
}