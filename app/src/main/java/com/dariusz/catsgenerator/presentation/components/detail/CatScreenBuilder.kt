package com.dariusz.catsgenerator.presentation.components.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.dariusz.catsgenerator.domain.model.*

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@Composable
fun CatScreenBuilder(
    listOfTags: List<Tag>,
    listOfColors: List<Color>,
    listOfFilters: List<Filter>,
    action: (Text?, Filter?, Tag?, Color?, Size?) -> Unit,
    finalUrl: String
) {

    var url by remember { mutableStateOf("https://cataas.com/cat") }

    var text by remember(Text) { mutableStateOf("") }
    var filter by remember(Filter) { mutableStateOf("") }
    var tag by remember(Tag) { mutableStateOf("") }
    var color by remember(Color) { mutableStateOf("") }
    var size by remember(Size) { mutableStateOf(0f) }

    var moreOptions by remember { mutableStateOf(false) }
    var notFound by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        item {

            CatPicture(
                url = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 400.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            DropDownList(
                "Choose tag",
                listOfTags
            ) {
                tag = it
            }

            Spacer(modifier = Modifier.height(6.dp))

            DropDownList(
                "Choose filter",
                listOfFilters
            ) {
                filter = it
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = moreOptions,
                    onCheckedChange = { moreOptions = it },
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text("Add text to image")
            }

            Spacer(modifier = Modifier.height(6.dp))

            if (moreOptions) {

                TextBox(
                    "Add description",
                    text
                ) {
                    text = it
                }
                Spacer(modifier = Modifier.height(3.dp))

                Text("Text size: ${size.toInt()}", modifier = Modifier.padding(5.dp))

                SizeSlider(value = size,
                    onValueChange = {
                        size = it
                    }
                )

                Spacer(modifier = Modifier.height(3.dp))

                DropDownList(
                    "Choose color of the description",
                    listOfColors
                ) {
                    color = it
                }

            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                onClick = {
                    action.invoke(
                        text, filter, tag, color, size.toInt().toString()
                    )
                    when (finalUrl) {
                        "" -> {
                            notFound = true
                        }
                        else -> {
                            url = finalUrl
                        }
                    }
                }
            ) {
                if (notFound)
                    BottomSnackbar(
                        "Cat not found. Add more attributes"
                    ) {
                        notFound = it
                    }
                else
                    Text(
                        text = "Give me the cat!",
                        modifier = Modifier.padding(16.dp),
                        color = androidx.compose.ui.graphics.Color.White
                    )
            }
        }
    }

}