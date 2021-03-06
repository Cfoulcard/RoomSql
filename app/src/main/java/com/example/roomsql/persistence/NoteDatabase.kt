package com.example.roomsql.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomsql.models.Note

/**
 * Room Database - Responsible for accessing the database
 *
 * Exported schema will be in a JSON file: https://developer.android.com/training/data-storage/room/migrating-db-versions
 * Change version if the entity changes! Exported data is stored within the project under
 * the schemas folder after implementing Gradle export schemas in link above.
 */
@Database(entities = [Note::class], version = 1)

abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {

        const val DATABASE_NAME = "notes_db"
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}