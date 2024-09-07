package com.example.movemate.ui.shipments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.IndicatorOrange
import com.example.movemate.ui.theme.Midnight
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.PurpleWhite
import com.example.movemate.ui.theme.ShadePurple
import com.example.movemate.ui.utils.TopBar
import kotlinx.coroutines.delay


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShipmentsTabLayout(
    onBackPressed: () -> Unit = {},
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        TabItem(stringResource(R.string.all)),
        TabItem(stringResource(R.string.completed)),
        TabItem(stringResource(R.string.in_progress_title)),
        TabItem(stringResource(R.string.pending_order)),
        TabItem(stringResource(id = R.string.cancelled)),
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
        TopBar(stringResource(id = R.string.shipment_history), onBackPressed)
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
                    val shipments = shipmentItems(index)
                    val isSelected = selectedTabIndex == index
                    val countBackgroundColor = if (isSelected) IndicatorOrange else ShadePurple
                    Tab(
                        modifier = Modifier.align(Alignment.Start),
                        selected = isSelected,
                        selectedContentColor = Color.White,
                        unselectedContentColor = PurpleWhite,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(text = tab.name, fontSize = 13.sp)

                                Spacer(modifier = Modifier.size(4.dp))

                                Text(
                                    modifier = Modifier
                                        .background(
                                            color = countBackgroundColor,
                                            shape = RoundedCornerShape(12.dp)
                                        )
                                        .width(30.dp)
                                        .height(20.dp),
                                    text = "${shipments.size}",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    )
                }
            }

            Shipments(selectedTabIndex)
        }
    }
}

@Composable
private fun shipmentItems(index: Int): List<ShipmentItem> {
    val shipments = when (index) {
        0 -> getShipments()
        1 -> getShipments(ShipmentStatus.LOADING)
        2 -> getShipments(ShipmentStatus.IN_PROGRESS)
        3 -> getShipments(ShipmentStatus.PENDING)
        else -> getShipments(ShipmentStatus.CANCELLED)
    }
    return shipments
}

fun getShipments(status: ShipmentStatus? = null): List<ShipmentItem> {
    val shipments = listOf(
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222311",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222312",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.LOADING,
            shipmentCode = "NEJ200899341222313",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.LOADING,
            shipmentCode = "NEJ200899341222314",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222315",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222316",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222317",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222318",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222319",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222310",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899041222319",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.LOADING,
            shipmentCode = "NEJ200899341222328",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.LOADING,
            shipmentCode = "NEJ200899341222337",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222346",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222355",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222364",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222373",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.LOADING,
            shipmentCode = "NEJ200899341222382",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.PENDING,
            shipmentCode = "NEJ200899341222391",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
        ShipmentItem(
            shipmentStatus = ShipmentStatus.IN_PROGRESS,
            shipmentCode = "NEJ200899341222300",
            origin = "Atlanta",
            cost = "$1400",
            date = "Sep 20, 2023"
        ),
    )
    status?.let {
        return shipments.filter { it.shipmentStatus == status }
    }
    return shipments
}

data class TabItem(val name: String)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Shipments(index: Int) {
    val shipments = shipmentItems(index)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
    ) {
        var showTopBox by remember(shipments) { mutableStateOf(false) }
        LaunchedEffect(index) {
            delay(10)
            showTopBox = true
        }

        if (shipments.isEmpty()) return

        LazyColumn(
            Modifier
                .background(OffWhite)
                .padding(horizontal = 16.dp)
                .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.size(4.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.shipments),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Midnight
                    )
                )
            }
            items(shipments, key = { it.shipmentCode }) { shipment ->
                AnimatedVisibility(
                    visible = showTopBox,
                    exit = ExitTransition.None,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = LinearOutSlowInEasing,
                        )
                    ) + slideInVertically { fullHeight -> fullHeight }
                ) {
                    Shipment(
                        modifier = Modifier
                            .animateItemPlacement(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessMediumLow,
                                    visibilityThreshold = IntOffset.VisibilityThreshold
                                )
                            ), shipment
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShipmentsPreview() {
    ShipmentsTabLayout()
}