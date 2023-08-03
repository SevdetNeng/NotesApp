package com.sevdetneng.notesapp.data

import androidx.compose.runtime.remember
import com.sevdetneng.notesapp.model.Note

class NotesDataSource(){
    fun loadNotes() : List<Note> {
        return listOf(Note(title = "new note", description = "shopping"),
            Note(title = "new note2", description = "driving"),
            Note(title = "new note3", description = "watching a movie"),
            Note(title = "new note4", description = "listening to music"),
            Note(title = "new note5", description = "family picnic"),
        )
    }
}