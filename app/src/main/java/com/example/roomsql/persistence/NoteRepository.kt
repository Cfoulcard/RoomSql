package com.example.roomsql.persistence

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomsql.async.InsertAsyncTask
import com.example.roomsql.models.Note

/**
 * Makes calls to the DAO -- Acts as an ambassador between the activity/fragments and Database
 */
class NoteRepository(context: Context?) {

    private val mNoteDatabase: NoteDatabase? = context?.let { NoteDatabase.getInstance(it) }

    fun insertNoteTask(note: Note?) {
        if (mNoteDatabase != null) {
            InsertAsyncTask(mNoteDatabase.noteDao).execute(note)
        }
    }

    fun deleteNoteTask(note: Note?) {}

    fun updateNoteTask(note: Note?) {}

    fun retrieveNotesTask(): LiveData<List<Note>>? = mNoteDatabase?.noteDao?.getNotes()

    }

