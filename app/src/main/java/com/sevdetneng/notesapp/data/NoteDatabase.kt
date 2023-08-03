package com.sevdetneng.notesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sevdetneng.notesapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDatabaseDao
}