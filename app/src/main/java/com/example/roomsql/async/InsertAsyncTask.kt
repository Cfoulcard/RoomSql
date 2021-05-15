package com.example.roomsql.async

import android.os.AsyncTask
import com.example.roomsql.models.Note
import com.example.roomsql.persistence.NoteDao

/**
 * Used to perform background task, in this case adding notes using the Note class template via
 * the NoteDAO
 */
class InsertAsyncTask(private val mNoteDao: NoteDao) : AsyncTask<Note?, Void?, Void?>() {

    override fun doInBackground(vararg notes: Note?): Void? {
        mNoteDao.insertNotes(*notes)
        return null
    }

}