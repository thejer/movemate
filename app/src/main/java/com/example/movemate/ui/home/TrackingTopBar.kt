package com.example.movemate.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.R
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.PurpleWhite
import com.example.movemate.ui.theme.PurpleWhite2

@Composable
fun TrackingTopBar(
    isVisible: Boolean,
    focusManager: FocusManager
): Boolean {
    var isVisibleState = isVisible

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple)
            .padding(top = 16.dp)
    ) {
        val (header, backButton) = createRefs()

        val exitTransition = fadeOut(
            animationSpec = tween(
                durationMillis = 100,
                easing = FastOutLinearInEasing
            )
        ) + shrinkVertically(
            animationSpec = tween(
                durationMillis = 280,
                delayMillis = 10,
                easing = FastOutLinearInEasing
            )
        )
        val enterTransition = fadeIn(
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearOutSlowInEasing,
                delayMillis = 30
            )
        ) + expandVertically(animationSpec = tween(durationMillis = 100))
        AnimatedVisibility(
            modifier = Modifier.constrainAs(header) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            visible = isVisibleState,
            exit = exitTransition,
            enter = enterTransition
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (profileImage, locationColumn, notification) = createRefs()
                Image(
                    painter = painterResource(id = R.drawable.profile_image),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(profileImage) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 16.dp)
                        }
                        .size(40.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .constrainAs(locationColumn) {
                            top.linkTo(profileImage.top)
                            start.linkTo(profileImage.end, margin = 16.dp)
                            bottom.linkTo(profileImage.bottom)
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_location),
                            contentDescription = "location icon",
                            Modifier.size(18.dp),
                            tint = PurpleWhite
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Text(
                            text = "Your location",
                            color = PurpleWhite,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(1.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Wertheimer, Illinois",
                            fontSize = 18.sp,
                            color = PurpleWhite2
                        )
                        Spacer(modifier = Modifier.width(3.dp))
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_expand_more),
                            contentDescription = "expand icon",
                            Modifier.size(18.dp),
                            tint = PurpleWhite2,
                        )
                    }

                }

                Box(modifier = Modifier
                    .background(shape = CircleShape, color = Color.White)
                    .size(40.dp)
                    .constrainAs(notification) {
                        top.linkTo(profileImage.top)
                        end.linkTo(parent.end, margin = 16.dp)
                        bottom.linkTo(profileImage.bottom)
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_notification),
                        contentDescription = "notification icon",
                        Modifier
                            .size(24.dp)
                            .align(Alignment.Center),
                        tint = Purple,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(end = 16.dp)
                .constrainAs(backButton) {
                    top.linkTo(header.bottom, margin = 20.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 18.dp)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(visible = isVisibleState.not()) {
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        modifier = Modifier.clickable { focusManager.clearFocus() },
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "back button",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
            AnimatedVisibility(visible = isVisibleState) {
                Spacer(modifier = Modifier.width(16.dp))
            }
            SearchView(modifier = Modifier.fillMaxWidth(), onFocusChange = {
                isVisibleState = it.isFocused.not()
            })
        }
    }
    return isVisibleState
}