package com.example.movemate.ui

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.example.movemate.R

@Composable
fun BackButton(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Icon(
        modifier = modifier
            .clickable { onBackPressed() }, // Go back on click
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
        contentDescription = "back button",
        tint = Color.White
    )
}