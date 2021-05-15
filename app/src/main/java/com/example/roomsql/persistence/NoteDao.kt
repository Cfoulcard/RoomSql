package com.example.roomsql.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomsql.models.Note

/**
 * DAO interface for Note. Used to tell Room to access the database.
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