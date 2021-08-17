package com.dariusz.catsgenerator.presentation.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.dariusz.catsgenerator.domain.model.Text

@ExperimentalCoilApi
@Composable
fun CatPicture(
    url: String,
    modifier: Modifier,
    description: String? = null,
) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
            allowHardware(false)
        },
        imageLoader = LocalImageLoader.current,
    )

    Box {
        Image(
            painter = painter,
            contentDescription = description,
            modifier = modifier
        )
        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is ImagePainter.State.Error -> {
                Text("Error loading image, url: $url", Modifier.align(Alignment.Center))
            }
            else -> {
                //do nothing
            }
        }
    }
}


@ExperimentalComposeUiApi
@Composable
fun TextBox(
    label: String,
    value: Text,
    onValueChange: (Text) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange.invoke(it)
            },
            label = { Text(label) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        )
    }
}

@Composable
fun DropDownList(
    label: String,
    input: List<String>,
    action: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var dropDownWidth by remember { mutableStateOf(0) }

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp
    else
        Icons.Filled.ArrowDropDown

    Column {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged {
                    dropDownWidth = it.width
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
            },
            readOnly = true
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.toDp() })
                .height(300.dp)
        ) {
            input.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    action.invoke(selectedText)
                    expanded = !expanded
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun SizeSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
) {
    Slider(
        value = value,
        onValueChange = {
            onValueChange.invoke(it)
        },
        steps = 60,
        valueRange = 0f..60f
    )
}

@Composable
fun BottomSnackbar(
    text: String,
    action: (Boolean) -> Unit
) {
    Column {
        var snackbarVisibleState by remember { mutableStateOf(true) }
        if (snackbarVisibleState) {
            Snackbar(
                action = {
                    Button(onClick = {
                        snackbarVisibleState = false
                        action.invoke(snackbarVisibleState)
                    }) {
                        Text("Got it")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text) }
        }
    }
}