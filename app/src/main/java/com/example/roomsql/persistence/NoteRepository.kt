package com.example.roomsql.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomsql.models.Note

/**
 * Makes calls to the DAO -- Acts as an ambassador between the activity/fragments and Database
 */
class NoteRepository(context: Context) {

    private val mNoteDatabase: NoteDatabase? = null

    init {
        mNoteDatabase?.getInstance(context)
    }

    fun insertNoteTask(note: Note) {

    }

    fun updateNoteTask(note: Note) {

    }

    fun retrieveNotesTask(): LiveData<MutableList<Note?>?>? {
        return null
    }

    fun deleteNoteTask(note: Note) {

    }
}