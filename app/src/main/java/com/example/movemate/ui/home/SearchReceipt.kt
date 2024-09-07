package com.example.movemate.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.Graphite
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.theme.Purple
import com.example.movemate.ui.theme.SlateGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchReceipt(modifier: Modifier = Modifier) {
    val focusManager = LocalFocusManager.current

    val items = listOf(
        Item(
            packageName = "Macbook pro M2",
            receiptNumber = "NE43857340857904",
            origin = "Paris",
            destination = "Morocco"
        ),
        Item(
            packageName = "Summer linen jacket",
            receiptNumber = "NEJ20089934122231",
            origin = "Barcelona",
            destination = "Paris"
        ),
        Item(
            packageName = "Tapered-fit jeans AW",
            receiptNumber = "NEJ35870264978649",
            origin = "Colombia",
            destination = "Paris"
        ),
        Item(
            packageName = "Slim fit jeans AW",
            receiptNumber = "NEJ35870264978259",
            origin = "Bogota",
            destination = "Dhaka"
        ),
        Item(
            packageName = "Office setup desk",
            receiptNumber = "NEJ23481570754963",
            origin = "France",
            destination = "Germany"
        ),
    )

    Box(
        modifier = modifier
            .background(OffWhite)
            .fillMaxWidth()
            .clickable { focusManager.clearFocus() }
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        Card(
            modifier = Modifier
                .background(Color.White),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                items(items, key = { it.receiptNumber }) { item ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp).animateItemPlacement()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(Purple, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_package),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(text = item.packageName, fontSize = 15.sp, color = Graphite, fontWeight = FontWeight.SemiBold)
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(text = item.getDescription(), fontSize = 11.sp, color = SlateGray)
                        }
                    }
                }
            }
        }
    }
}

data class Item(
    val packageName: String,
    val receiptNumber: String,
    val origin: String,
    val destination: String
) {
    fun getDescription() = "#$receiptNumber • $origin -> $destination"
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun PreviewSearchReceipt() {
    SearchReceipt()
}