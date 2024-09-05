package com.example.movemate.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.movemate.R
import com.example.movemate.ui.theme.GlowGreen
import com.example.movemate.ui.theme.Graphite
import com.example.movemate.ui.theme.MintGreen
import com.example.movemate.ui.theme.SlateGray
import com.example.movemate.ui.theme.SombreGray
import com.example.movemate.ui.theme.SweetOrange
import com.example.movemate.ui.theme.WeltOrange

@Composable
fun TrackingCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
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