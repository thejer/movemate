package com.example.movemate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.ui.theme.BackgroundGray
import com.example.movemate.ui.theme.GlowGreen
import com.example.movemate.ui.theme.Graphite
import com.example.movemate.ui.theme.MintGreen
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.theme.Orange
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.PurpleWhite
import com.example.movemate.ui.theme.PurpleWhite2
import com.example.movemate.ui.theme.RainyGray
import com.example.movemate.ui.theme.SlateGray
import com.example.movemate.ui.theme.SombreGray
import com.example.movemate.ui.theme.SweetOrange
import com.example.movemate.ui.theme.WeltOrange

@Composable
fun TrackingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(OffWhite)
    ) {
        TopBar()

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Tracking",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Graphite
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            TrackingCard()

            Spacer(modifier = Modifier.height(24.dp))
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Available vehicles",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )

        Spacer(modifier = Modifier.height(16.dp))
        AvailableVehicles(
            modifier = Modifier.padding(start = 16.dp),
        )

    }
}

@Composable
fun TopBar() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple)
    ) {

        val (profileImage, locationColumn, notification, search) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(profileImage) {
                    top.linkTo(parent.top, margin = 16.dp)
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
                    start.linkTo(profileImage.end, margin = 10.dp)
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

        SearchView(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 16.dp)
            .constrainAs(search) {
                top.linkTo(profileImage.bottom)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }) {

        }
    }
}

@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier
            .height(52.dp)
            .background(Color.White, shape = RoundedCornerShape(50.dp)),
        value = searchText,
        onValueChange = { searchText = it },
        leadingIcon = {
            Icon(
                modifier = Modifier
                    .size(28.dp)
                    .padding(start = 6.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Purple
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
                color = RainyGray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Purple,
            unfocusedBorderColor = Purple
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch(searchText) })
    )
}

@Composable
fun TrackingCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.White)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            val (shipmentNumber,
                shipmentNumberLabel,
                forkLiftIcon,
                divider,
                senderRow,
                receiverRow,
                senderTimeColumn,
                receiverStatus,
                bottomDivider,
                addStopRow
            ) = createRefs()

            Text(
                modifier = Modifier.constrainAs(shipmentNumberLabel) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                text = "Shipment Number",
                color = SlateGray,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier.constrainAs(shipmentNumber) {
                    top.linkTo(shipmentNumberLabel.bottom, margin = 8.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                text = "NEJ20089934122231",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Graphite
                )
            )

            Image(
                modifier = Modifier
                    .size(48.dp)
                    .constrainAs(forkLiftIcon) {
                        top.linkTo(parent.top, margin = 16.dp)
                        end.linkTo(parent.end, margin = 16.dp)
                    },
                painter = painterResource(id = R.drawable.ic_forklift),
                contentDescription = "forklift shipment image"
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .constrainAs(divider) {
                        top.linkTo(shipmentNumber.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = SombreGray
            )

            Row(
                modifier = Modifier.constrainAs(senderRow) {
                    top.linkTo(divider.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    Modifier
                        .padding(end = 6.dp)
                        .background(shape = CircleShape, color = WeltOrange)
                        .size(36.dp)
                ) {

                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_sender_box),
                        contentDescription = "sender icon image"
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = "Sender",
                        color = SlateGray,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Atlanta, 5243",
                        color = Graphite,
                        fontSize = 14.sp
                    )
                }
            }

            Column(modifier = Modifier.constrainAs(senderTimeColumn) {
                top.linkTo(senderRow.top)
                start.linkTo(receiverStatus.start)
            }) {
                Text(
                    text = "Time",
                    color = SlateGray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(8.dp),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_circle),
                        contentDescription = null,
                        tint = GlowGreen
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "2 day - 3 days",
                        color = Graphite,
                        fontSize = 14.sp
                    )
                }
            }

            Row(
                modifier = Modifier.constrainAs(receiverRow) {
                    top.linkTo(senderRow.bottom, margin = 30.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    Modifier
                        .padding(end = 6.dp)
                        .background(shape = CircleShape, color = MintGreen)
                        .size(36.dp)
                ) {

                    Image(
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_receiver_box),
                        contentDescription = "sender icon image"
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = "Receiver",
                        color = SlateGray,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = "Chicago, 6342",
                        color = Graphite,
                        fontSize = 14.sp
                    )
                }
            }

            Column(modifier = Modifier.constrainAs(receiverStatus) {
                top.linkTo(receiverRow.top)
                end.linkTo(parent.end, margin = 16.dp)
            }) {
                Text(
                    text = "Status",
                    color = SlateGray,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Waiting to collect",
                    color = Graphite,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier
                    .constrainAs(bottomDivider) {
                        top.linkTo(receiverRow.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = SombreGray
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .wrapContentWidth()
                    .constrainAs(addStopRow) {
                        top.linkTo(bottomDivider.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = "Add stop icon",
                    tint = SweetOrange,
                )

                Text(
                    text = "Add Stop",
                    color = SweetOrange,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun AvailableVehicles(modifier: Modifier = Modifier) {
    val vehicles = listOf(
        Vehicle("Ocean freight", "International", R.drawable.ic_ocean_freight),
        Vehicle("Cargo freight", "Reliable", R.drawable.ic_cargo_freight),
        Vehicle("Air freight", "International", R.drawable.ic_air_freight),
        Vehicle("Train freight", "Multi Service", R.drawable.ic_train_freight),
        Vehicle("Instant freight", "Local", R.drawable.ic_instant_freight),
        Vehicle("Road freight", "Local", R.drawable.ic_road_freight),
    )
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(vehicles, key = { it.titleText }) { vehicle ->
            VehicleCard(vehicle = vehicle)
        }
    }
}

data class Vehicle(
    val titleText: String,
    val subtitleText: String,
    val iconImage: Int
)

@Composable
fun VehicleCard(vehicle: Vehicle) {
    Card(
        modifier = Modifier
            .background(BackgroundGray)
            .width(120.dp)
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(BackgroundGray)
                .fillMaxWidth(),
        ) {
            val (title, subTitle, icon) = createRefs()

            Text(
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                },
                text = vehicle.titleText,
                color = Graphite,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier.constrainAs(subTitle) {
                    top.linkTo(title.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                },
                text = vehicle.subtitleText,
                color = SlateGray,
                fontSize = 12.sp
            )

            Image(
                modifier = Modifier
                    .height(90.dp)
                    .constrainAs(icon) {
                        top.linkTo(subTitle.bottom)
                        end.linkTo(parent.end)
                    },
                painter = painterResource(id = vehicle.iconImage),
                contentDescription = vehicle.titleText
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewTrackingScreen() {
    TrackingScreen()
}

@Preview(showBackground = true)
@Composable
fun PreviewVehicleCard() {
    VehicleCard(Vehicle("Air freight", "International", R.drawable.ic_air_freight))
}


@Preview(showBackground = true, backgroundColor = 0xFF543A9C)
@Composable
fun PreviewSearchView() {
    SearchView(onSearch = { query ->

    })
}

