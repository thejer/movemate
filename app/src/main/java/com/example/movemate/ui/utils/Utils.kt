package com.example.movemate.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.R
import com.example.movemate.ui.theme.Purple

@Composable
fun BackButton(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Icon(
        modifier = modifier
            .clickable { onBackPressed() },
        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
        contentDescription = stringResource(id = R.string.back_button),
        tint = Color.White
    )
}


@Composable
fun TopBar(headerString: String, onBackPressed: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .background(Purple)
            .fillMaxWidth()
    ) {
        val (backButton, header) = createRefs()
        BackButton(
            modifier = Modifier
                .padding(start = 16.dp, top = 11.dp, bottom = 11.dp)
                .clickable {}
                .constrainAs(backButton) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            onBackPressed = onBackPressed
        )
        Text(
            modifier = Modifier
                .constrainAs(header) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                },
            text = headerString,
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White
            )
        )
    }
}