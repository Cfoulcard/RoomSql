package com.example.roomsql.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomsql.async.InsertAsyncTask
import com.example.roomsql.models.Note

/**
 * Makes calls to the DAO -- Acts as an ambassador between the activity/fragments and Database
 */
class NoteRepository(context: Context) {

    private val mNoteDatabase: NoteDatabase? = null

    init {
        mNoteDatabase?.getInstance(context)
        val insertAsyncTask: InsertAsyncTask
    }

    fun insertNoteTask(note: Note) {
        InsertAsyncTask(mNoteDatabase!!.getNoteDao()!!).execute(note)
    }

    fun updateNoteTask(note: Note) {

    }

    fun retrieveNotesTask(): LiveData<MutableList<Note?>?>? {
        return null
    }

    fun deleteNoteTask(note: Note) {

    }
}