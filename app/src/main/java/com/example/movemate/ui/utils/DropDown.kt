package com.example.movemate.ui.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.DarkGray
import com.example.movemate.ui.theme.Mercury
import com.example.movemate.ui.theme.Midnight
import com.example.movemate.ui.theme.OffWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownWithImageAndText() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(
        DropDownItem(R.string.box, R.drawable.ic_package),
        DropDownItem(R.string.bag, R.drawable.ic_bag),
        DropDownItem(R.string.open, R.drawable.ic_open)
    )

    var selectedItem by remember { mutableStateOf(items[0].name) }

    Box {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = stringResource(id = selectedItem),
                onValueChange = {},
                readOnly = true,
                textStyle = TextStyle.Default.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Midnight
                ),
                prefix = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = R.drawable.ic_box),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.size(5.dp))

                        VerticalDivider(
                            modifier = Modifier.height(30.dp),
                            color = DarkGray,
                            thickness = .5.dp
                        )

                        Spacer(modifier = Modifier.size(5.dp))
                    }
                },
                trailingIcon = {
                    Icon(
                        tint = Mercury,
                        modifier = Modifier.rotate(if (expanded) 180f else 0f),
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_expand_more),
                        contentDescription = stringResource(
                            id = R.string.expand_icon
                        )
                    )
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp)
            )
            ExposedDropdownMenu(
                modifier = Modifier.background(OffWhite),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            DropDownMenuItem(item)
                        },
                        onClick = {
                            selectedItem = item.name
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

data class DropDownItem(
    @StringRes val name: Int,
    @DrawableRes val icon: Int
)

@Composable
fun DropDownMenuItem(dropDownItem: DropDownItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(OffWhite, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            tint = Mercury,
            imageVector = ImageVector.vectorResource(id = dropDownItem.icon),
            contentDescription = stringResource(id = dropDownItem.name)
        )

        Spacer(modifier = Modifier.size(5.dp))

        VerticalDivider(modifier = Modifier.height(30.dp), color = DarkGray, thickness = .5.dp)

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = stringResource(id = dropDownItem.name),
            style = TextStyle.Default.copy(
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Mercury
            ),
        )

    }
}

@Preview
@Composable
fun DropDownItemPreview() {
    DropDownMenuItem(DropDownItem(R.string.box, R.drawable.ic_sender))
}

@Preview
@Composable
fun DropdownWithImageAndTextPreview() {
    DropdownWithImageAndText()
}