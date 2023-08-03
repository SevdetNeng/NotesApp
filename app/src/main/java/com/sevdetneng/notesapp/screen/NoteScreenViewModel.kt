package com.sevdetneng.notesapp.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdetneng.notesapp.model.Note
import com.sevdetneng.notesapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewModel @Inject constructor(val noteRepository: NoteRepository) : ViewModel() {
    //private var noteList = mutableStateListOf<Note>()
    var removedNote = Note(title = "", description = "")
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged()
                .collect{ listOfNotes ->
                        _noteList.value = listOfNotes
                }
        }
    }

    fun addNote(note : Note) = viewModelScope.launch { noteRepository.addNote(note) }

    fun removeNote(note : Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
        removedNote = note
    }

    fun updateNote(note : Note) = viewModelScope.launch { noteRepository.updateNote(note) }

    fun restoreNote(note : Note) = viewModelScope.launch {
        noteRepository.addNote(note)
        removedNote = Note(title = "", description = "")
    }

}