package com.example.roomsql.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomsql.models.Note

/**
 * Room Database
 *
 * Exported schema will be in a JSON file: https://developer.android.com/training/data-storage/room/migrating-db-versions
 * Change version if the entity changes!
 */
@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
}