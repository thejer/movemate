package com.example.movemate.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SlideUpAnimation() {
    var showTopBox by remember { mutableStateOf(true) }
    val transitionState = updateTransition(targetState = showTopBox, label = "transition")

    val topBoxOffset by transitionState.animateDp(
        transitionSpec = {
//            if (false isTransitioningTo true) { // Disappearing
                tween(durationMillis = 250)
//            } else { // Appearing
//                tween(durationMillis = 250)
//            }
        }, label = "topBoxOffset"
    ) { show ->
        if (show) 0.dp else -100.dp // Negative offset to move it up and out of view
    }

    Column(modifier = Modifier
//        .offset(y = topBoxOffset)
    ) {
        AnimatedVisibility(
            modifier = Modifier, // Apply offset to top box
            visible = showTopBox,
            exit = fadeOut() + shrinkVertically(),
            enter = fadeIn() + expandVertically()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Red),

                ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize(),
                    text = "Top Box")
            }
        }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Blue)
                // Apply offset to bottom box
            ) {
                Text(
                    modifier = Modifier,
                    text = "Bottom Box"
                )
            }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { showTopBox = !showTopBox }) {
            Text(if (showTopBox) "Slide Up" else "Slide Down")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewAnim() {
    SlideUpAnimation()
}