package com.sevdetneng.notesapp.screen

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sevdetneng.notesapp.components.NoteButton
import com.sevdetneng.notesapp.components.NoteInputText
import com.sevdetneng.notesapp.components.NoteRow
import com.sevdetneng.notesapp.data.NotesDataSource
import com.sevdetneng.notesapp.model.Note

@Composable
fun NoteScreen(notes : List<Note>,
               onAddNote : (Note) -> Unit,
               onRemoveNote : (Note) -> Unit,
               onRestoreNote : () -> Unit
){
    var noteNameState by remember { mutableStateOf("") }
    var noteDescriptionState by remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold(topBar = {
//        TopAppBar(backgroundColor = Color.Gray, ){
//            Text("Notes", fontSize = 24.sp)
//        }
        TopAppBar(title = {
            Text("Notes")
        },
        actions = {
            Icon(imageVector = Icons.Rounded.Refresh,
                contentDescription = "placeholder",
                modifier = Modifier.clickable {
                    onRestoreNote()
                }
            )
        },
        backgroundColor = Color.Gray)
    }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)){
            NoteInputText(text = noteNameState, label = "Title", onTextChange = { noteName ->
                noteNameState = noteName

            } )
            Spacer(Modifier.height(8.dp))
            NoteInputText(text = noteDescriptionState, label = "Note", onTextChange = { noteDesc ->
                noteDescriptionState = noteDesc

            } )
            Spacer(Modifier.height(16.dp))
            NoteButton(text = "Save Note", enabled = true, onClick = {
                if(noteNameState.isNotEmpty() && noteDescriptionState.isNotEmpty()){
                    val note = Note(title = noteNameState, description = noteDescriptionState)
                    onAddNote(note)
                    noteDescriptionState = ""
                    noteNameState = ""
                }
                Toast.makeText(context,"Note Added!",Toast.LENGTH_LONG).show()
            } )
            Divider(modifier = Modifier.padding(10.dp))
            LazyColumn{
                items(notes){ note ->
                    NoteRow(note, onNoteClicked = { note ->
                        onRemoveNote(note)
                    })
                }
            }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {}, onRestoreNote = {})
}