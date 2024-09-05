package com.example.movemate.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.R
import com.example.movemate.ui.theme.BackgroundGray
import com.example.movemate.ui.theme.Midnight
import com.example.movemate.ui.theme.SlateGray

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
            .width(135.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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
                color = Midnight,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.constrainAs(subTitle) {
                    top.linkTo(title.bottom, margin = 4.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                },
                text = vehicle.subtitleText,
                color = SlateGray,
                fontSize = 14.sp
            )

            Image(
                modifier = Modifier
                    .height(100.dp)
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
fun PreviewVehicleCard() {
    VehicleCard(Vehicle("Air freight", "International", R.drawable.ic_air_freight))
}

