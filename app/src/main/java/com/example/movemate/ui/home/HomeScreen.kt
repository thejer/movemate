package com.example.movemate.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.R
import com.example.movemate.ui.theme.Midnight
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.PurpleWhite
import com.example.movemate.ui.theme.PurpleWhite2
import com.example.movemate.ui.utils.BackButton

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
    ) {
        val focusManager = LocalFocusManager.current

        var isVisible by remember { mutableStateOf(true) }
        var showReceipts by remember { mutableStateOf(true) }

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
            androidx.compose.animation.AnimatedVisibility(
                modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                visible = isVisible,
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
                                contentDescription = stringResource(R.string.location_icon),
                                Modifier.size(18.dp),
                                tint = PurpleWhite
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Text(
                                text = stringResource(R.string.your_location),
                                color = PurpleWhite,
                                fontSize = 14.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(1.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = stringResource(R.string.wertheimer_illinois),
                                fontSize = 18.sp,
                                color = PurpleWhite2
                            )
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_expand_more),
                                contentDescription = stringResource(R.string.expand_icon),
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
                            contentDescription = stringResource(R.string.notification_icon),
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
                AnimatedVisibility(visible = isVisible.not()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        BackButton(onBackPressed = { focusManager.clearFocus() })
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }
                AnimatedVisibility(visible = isVisible) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
                SearchView(modifier = Modifier.fillMaxWidth(), onFocusChange = {
                    isVisible = it.isFocused.not()
                })
            }
        }

        Column {
            if (isVisible) TrackingScreen(focusManager)
            AnimatedVisibility(
                visible = isVisible.not() && showReceipts,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 600,
                        easing = LinearOutSlowInEasing,
                    )
                ) + slideInVertically { fullHeight -> fullHeight },
            ) {
                SearchReceipt(modifier = Modifier)
            }

            Spacer(modifier = Modifier.size(27.dp))
        }
    }
}

@Composable
private fun TrackingScreen(focusManager: FocusManager) {
    Column(
        Modifier.clickable { focusManager.clearFocus() }
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.tracking),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Midnight
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            TrackingCard()

            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = stringResource(R.string.available_vehicles),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Midnight
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))
        AvailableVehicles(
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}
