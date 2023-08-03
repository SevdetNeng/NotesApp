package com.sevdetneng.notesapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.sevdetneng.notesapp.model.Note

class NoteScreenViewModel : ViewModel() {
    private var noteList = mutableStateListOf<Note>()
    public var removedNote = Note(title = "", description = "")

    fun addNote(note : Note){
        noteList.add(note)
    }
    fun removeNote(note : Note){
        noteList.remove(note)
        removedNote = note
    }
    fun getList() : List<Note> {
        return noteList
    }
    fun restoreNote(note : Note){
        addNote(removedNote)
        removedNote = Note(title = "", description = "")
    }
}