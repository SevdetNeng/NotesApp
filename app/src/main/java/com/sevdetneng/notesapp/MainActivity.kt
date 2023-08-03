package com.sevdetneng.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sevdetneng.notesapp.data.NotesDataSource
import com.sevdetneng.notesapp.model.Note
import com.sevdetneng.notesapp.screen.NoteScreen
import com.sevdetneng.notesapp.screen.NoteScreenViewModel
import com.sevdetneng.notesapp.ui.theme.NotesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteViewModel : NoteScreenViewModel by viewModels()
        setContent {
            NotesAppTheme {
                notesApp(noteViewModel)
            }
        }
    }
}

@Composable
fun notesApp(viewModel : NoteScreenViewModel = viewModel()){
    val notes = viewModel.noteList.collectAsState().value
    NoteScreen(notes = notes, onAddNote = {
        viewModel.addNote(it)
    }, onRemoveNote = {
        viewModel.removeNote(it)
    }, onRestoreNote = {
        if(viewModel.removedNote.title != ""){
            viewModel.restoreNote(viewModel.removedNote)
        }
    }, onUpdateNote = {
        viewModel.updateNote(it)
    })
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesAppTheme {
        Greeting("Android")
    }
}