package com.example.roomsql.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomsql.models.Note

/**
 * DAO interface for Note. Used to tell Room how to access the database. These are performed on
 * a background Async task on a background thread, not the main thread. See the respective classes
 * in the async package directory
 */

@Dao
interface NoteDao {

    @Insert
    fun insertNotes(vararg notes: Note?): LongArray?

    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<List<Note>>?

    @Delete
    fun delete(vararg notes: Note?): Int

    @Update
    fun updateNotes(vararg notes: Note?): Int
}