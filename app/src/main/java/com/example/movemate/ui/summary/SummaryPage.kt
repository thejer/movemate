package com.example.movemate.ui.summary

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.CashGreen
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.theme.Orange
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.SlateGray
import com.example.movemate.ui.utils.AnimatedButton
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun SummaryPage(
    onBackToHome: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(
            color = OffWhite,
            darkIcons = true
        )
    }

    DisposableEffect(key1 = Unit) {
        onDispose {
            systemUiController.setStatusBarColor(
                color = Purple,
                darkIcons = false
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(OffWhite),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(97.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.movemate),
                style = TextStyle.Default.copy(
                    color = Purple,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            )

            Spacer(modifier = Modifier.size(18.dp))
            Icon(
                tint = Orange,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_move),
                contentDescription = "MoveMate icon"
            )
        }

        Spacer(modifier = Modifier.size(84.dp))

        Image(
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.ic_summary_box),
            contentDescription = stringResource(
                R.string.package_icon
            )
        )

        Spacer(modifier = Modifier.size(35.dp))

        Text(
            text = stringResource(R.string.total_estimated_amount),
            style = TextStyle.Default.copy(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            CountUp(start = 1200, limit = 1460)
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "USD",
                style = TextStyle.Default.copy(
                    color = CashGreen,
                    fontSize = 20.sp,
                )
            )
        }

        Spacer(modifier = Modifier.size(16.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 48.dp),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.disclaimer_text),
            style = TextStyle.Default.copy(
                color = SlateGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )

        Spacer(modifier = Modifier.size(24.dp))

        AnimatedButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = R.string.back_to_home
        ) {
            onBackToHome()
        }
    }
}

@Preview
@Composable
fun SummaryPagePreview() {
    SummaryPage()
}

@Composable
fun CountUp(start: Int = 0, limit: Int) {
    var count by remember { mutableIntStateOf(start) }

    LaunchedEffect(count) {
        if (count < limit) {
            delay(10)
            count += 3
        }
    }

    Text(
        text = "$${count.coerceAtMost(limit)}",
        style = TextStyle.Default.copy(
            color = CashGreen,
            fontSize = 28.sp,
        )
    )
}