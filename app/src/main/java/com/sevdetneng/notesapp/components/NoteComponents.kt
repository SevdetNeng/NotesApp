@file:OptIn(ExperimentalComposeUiApi::class)

package com.sevdetneng.notesapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@Composable
fun NoteInputText(modifier : Modifier = Modifier,
                  text : String,
                  label : String,
                  maxLines : Int = 1,
                  onTextChange : (String) -> Unit,
                  onImeAction: () -> Unit = {}

){
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier,
        label = {Text(label)}
    )
}

@Composable
fun NoteButton(modifier : Modifier = Modifier,
               text : String,
               onClick : () -> Unit = {},
               enabled : Boolean
){
    Button(onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
    ){
        Text(text)
    }
}