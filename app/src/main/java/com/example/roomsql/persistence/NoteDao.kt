package com.example.roomsql.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomsql.models.Note

/**
 * DAO interface for Note. Used to tell Room to access the database.
 */

@Dao
interface NoteDao {

    // val mNoteDatabase: NoteDatabase?

    // Holds all the arrays that were inserted into the database
    @Insert
    fun insertNotes(notes: Array<out Note?>)

    // SQL search that can be interpreted as "Select all from the notes table"
    @Query("SELECT * FROM notes")
    fun getNotes(): LiveData<MutableList<Note?>?>?

    // Deletes the Note as per the Int it is assigned to
    @Delete
    fun delete(notes: Note?): Int

    // Similar to delete, but tracks changed made to the rows
    @Update
    fun update(notes: Note?): Int


}