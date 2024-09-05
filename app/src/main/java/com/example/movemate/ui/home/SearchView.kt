package com.example.movemate.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.Orange
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.RainyGray

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    onFocusChange: (FocusState) -> Unit = {},
    onSearch: (String) -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }

    TextField(
        modifier = modifier
            .onFocusChanged { onFocusChange(it) }
            .wrapContentHeight(),
        value = searchText,
        onValueChange = { searchText = it },
        prefix = {
            Icon(
                modifier = Modifier
                    .padding(end = 6.dp)
                    .size(18.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
            )
        },
        trailingIcon = {
            Box(
                Modifier
                    .padding(end = 6.dp)
                    .background(shape = CircleShape, color = Orange)
                    .size(40.dp)
                    .clickable {
                        searchText = ""
                    }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_receipt),
                    contentDescription = "Receipt Icon",
                    Modifier
                        .size(16.dp)
                        .align(Alignment.Center),
                    tint = Color.White,
                )
            }
        },
        shape = RoundedCornerShape(50.dp),
        placeholder = {
            Text(
                text = "Enter the receipt number...",
                fontSize = 15.sp
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedLeadingIconColor = Purple,
            unfocusedLeadingIconColor = Purple,
            focusedPlaceholderColor = RainyGray,
            unfocusedPlaceholderColor = RainyGray,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(searchText) })
    )
}


@Preview(showBackground = true, backgroundColor = 0xFF543A9C)
@Composable
fun PreviewSearchView() {
    SearchView()
}

