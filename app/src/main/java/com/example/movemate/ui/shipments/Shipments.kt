package com.example.movemate.ui.shipments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.ui.BackButton
import com.example.movemate.ui.theme.IndicatorOrange
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.PurpleWhite
import com.example.movemate.ui.theme.ShadePurple


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Shipments(
    onBackPressed: () -> Unit = {},
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        TabItem("All", 10),
        TabItem("Completed", 5),
        TabItem("In Progress", 3),
        TabItem("Pending Order", 2),
        TabItem("Cancelled", 1),
    )
    val pagerState = rememberPagerState {
        tabs.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedTabIndex = pagerState.currentPage
    }

    Column(Modifier.background(Purple)) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple)
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
                text = "Shipment history",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 16.dp,
                containerColor = Purple,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        color = IndicatorOrange,
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[selectedTabIndex])
                            .fillMaxWidth()
                    )
                }
            ) {
                tabs.forEachIndexed { index, tab ->

                    val isSelected = selectedTabIndex == index
                    val countBackgroundColor = if (isSelected) IndicatorOrange else ShadePurple
                    Tab(
                        modifier = Modifier.align(Alignment.Start),
                        selected = isSelected,
                        selectedContentColor = Color.White,
                        unselectedContentColor = PurpleWhite,
                        onClick = { selectedTabIndex = index },
                        text = {
                            ConstraintLayout(modifier = Modifier.align(Alignment.Start)) {
                                val (text, count) = createRefs()
                                Text(
                                    modifier = Modifier.constrainAs(text) {
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                        start.linkTo(parent.start)
                                    }, text = tab.name,
                                    fontSize = 13.sp
                                )

                                Text(
                                    modifier = Modifier
                                        .background(
                                            countBackgroundColor,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .width(30.dp)
                                        .height(20.dp)
                                        .constrainAs(count) {
                                            top.linkTo(text.top)
                                            bottom.linkTo(text.bottom)
                                            start.linkTo(text.end, margin = 4.dp)
                                        },
                                    text = "${tab.count}",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = tabs[index].name)
                }
            }

            // Content for each tab goes here
            when (selectedTabIndex) {
                0 -> AllContent()
                1 -> CompletedContent()
                2 -> InProgressContent()
                3 -> PendingOrderContent()
                4 -> CancelledContent()
            }
        }
    }
}

data class TabItem(val name: String, val count: Int)

// Placeholder composables for tab content
@Composable
fun AllContent() {
    Text("Content for All tab")
}

@Composable
fun CompletedContent() {
    Text("Content for Completed tab")
}

@Composable
fun InProgressContent() {
    Text("Content for In Progress tab")
}

@Composable
fun PendingOrderContent() {
    Text("Content for Pending Order tab")
}

@Composable
fun CancelledContent() {
    Text("Content for Cancelled tab")
}

@Preview(showBackground = true)
@Composable
fun ShipmentsPreview() {
    Shipments()
}