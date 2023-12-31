package com.sevdetneng.notesapp.components

import android.graphics.Paint.Align
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sevdetneng.notesapp.model.Note

@Preview(showSystemUi = true)
@Composable
fun NoteRow(note : Note = Note(title = "Note", description = "Note Desc"),
            modifier: Modifier = Modifier,
            onNoteClicked : (Note) -> Unit = {},
            onEditClicked : (Note) -> Unit = {}
){
    Card(shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable { onNoteClicked(note) },
        backgroundColor = Color.LightGray
    ){
        Column(modifier = Modifier
            .padding(8.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ){
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()){
                Column(){
                    Text(note.title, fontWeight = FontWeight.Normal, fontSize = 12.sp)
                    Text(note.description, fontWeight = FontWeight.Light, fontSize = 16.sp)
                }
                Icon(imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    modifier = Modifier.size(16.dp)
                    .clickable {
                        onEditClicked(note)
                    })

            }

        }
    }
}