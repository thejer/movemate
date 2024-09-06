package com.example.movemate.ui.calculate

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.movemate.R
import com.example.movemate.ui.TopBar

@Composable
fun CalculateScreen(
    onBackPressed: () -> Unit = {},
) {
    Column {
        TopBar(stringResource(R.string.calculate), onBackPressed)

    }
}

@Preview
@Composable
fun CalculateScreenPreview() {
    CalculateScreen()
}