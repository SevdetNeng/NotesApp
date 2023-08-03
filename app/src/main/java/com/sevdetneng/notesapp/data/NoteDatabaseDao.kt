package com.sevdetneng.notesapp.data

import androidx.room.*
import com.sevdetneng.notesapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {
    @Query(value = "SELECT * from notes_table")
    fun getNotes() : Flow<List<Note>>

    @Query("SELECT * from notes_table where id =:id")
    suspend fun getNoteById(id : String) : Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note : Note)

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun updateNote(note : Note)

    @Query("DELETE FROM notes_table")
    suspend fun deleteAll()

    @Delete
    suspend fun deleteNote(note : Note)
}
