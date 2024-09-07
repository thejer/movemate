package com.example.movemate.ui.calculate

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movemate.R
import com.example.movemate.ui.theme.DarkGray
import com.example.movemate.ui.theme.DeepSeaBlue
import com.example.movemate.ui.theme.Mercury
import com.example.movemate.ui.theme.Midnight
import com.example.movemate.ui.theme.OffWhite
import com.example.movemate.ui.utils.AnimatedButton
import com.example.movemate.ui.utils.DropdownWithImageAndText
import com.example.movemate.ui.utils.TopBar
import kotlinx.coroutines.delay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CalculateScreen(
    onBackPressed: () -> Unit = {},
    onCalculateClicked: () -> Unit = {},
) {
    Column {
        val scrollState = rememberScrollState()
        TopBar(stringResource(R.string.calculate), onBackPressed)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(OffWhite)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                text = stringResource(R.string.destination),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Midnight
                )
            )

            Spacer(modifier = Modifier.size(16.dp))

            DestinationCard()

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.packaging),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Midnight
                )
            )

            Spacer(modifier = Modifier.size(2.dp))

            Text(
                text = stringResource(R.string.what_are_you_sending),
                style = TextStyle.Default.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Mercury
                )
            )

            Spacer(modifier = Modifier.size(16.dp))

            DropdownWithImageAndText()

            Spacer(modifier = Modifier.size(24.dp))

            Text(
                text = stringResource(R.string.categories),
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = Midnight
                )
            )

            Spacer(modifier = Modifier.size(2.dp))

            Text(
                text = stringResource(R.string.what_are_you_sending),
                style = TextStyle.Default.copy(
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal,
                    color = Mercury
                )
            )

            Spacer(modifier = Modifier.size(16.dp))

            val selectedCategories = remember { mutableStateListOf<Int>() }

            val categories = listOf(
                R.string.documents,
                R.string.glass,
                R.string.liquid,
                R.string.food,
                R.string.electronics,
                R.string.product,
                R.string.others
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                categories.forEach { category ->
                    CategoryPill(
                        category = category,
                        isChecked = selectedCategories.contains(category)
                    ) { isChecked ->
                        Log.d("jerrydev", "CalculateScreen: $isChecked")
                        if (isChecked) {
                            selectedCategories.add(category)
                        } else {
                            selectedCategories.remove(category)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedButton(text = R.string.calculate, onClick = {
                onCalculateClicked()
            })
            Spacer(modifier = Modifier.size(27.dp))
        }
    }
}

private enum class PillState { Idle, Pressed }

@Composable
fun CategoryPill(
    @StringRes category: Int,
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    var pillState by remember { mutableStateOf(PillState.Idle) }
    val scale by animateFloatAsState(
        targetValue = if (pillState == PillState.Pressed) 0.95f else 1f,
        animationSpec = tween(durationMillis = 150),
        label = "pill animation"
    )

    val backgroundColor: Color
    val borderColor: Color
    val textColor: Color
    if (isChecked) {
        backgroundColor = DeepSeaBlue
        borderColor = Color.Transparent
        textColor = Color.White
    } else {
        backgroundColor = OffWhite
        borderColor = Mercury
        textColor = DeepSeaBlue
    }
    Row(
        modifier = Modifier
            .scale(scale)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .toggleable(
                value = isChecked,
                onValueChange = {
                    onCheckChange(!isChecked)
                    pillState = PillState.Pressed
                }
            )
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isChecked) {
            Icon(
                tint = textColor,
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = stringResource(R.string.selected_icon)
            )
        }

        Text(
            text = stringResource(id = category),
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = textColor
            )
        )
        LaunchedEffect(pillState) {
            if (pillState == PillState.Pressed) {
                delay(150)
                pillState = PillState.Idle
            }
        }
    }
}

@Preview
@Composable
fun CategoryPillPreview() {
    CategoryPill(R.string.electronics, false) {}
}

@Composable
fun DestinationCard() {
    Card(
        modifier = Modifier
            .background(OffWhite)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CalculateFieldItem(icon = R.drawable.ic_sender, placeholder = R.string.sender_location)

            CalculateFieldItem(
                icon = R.drawable.ic_receiver,
                placeholder = R.string.receiver_location
            )

            CalculateFieldItem(icon = R.drawable.ic_scale, placeholder = R.string.approx_weight)
        }
    }
}

@Composable
fun CalculateFieldItem(@DrawableRes icon: Int, @StringRes placeholder: Int) {
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
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = stringResource(id = placeholder)
        )

        Spacer(modifier = Modifier.size(5.dp))

        VerticalDivider(modifier = Modifier.height(30.dp), color = DarkGray, thickness = .5.dp)
        var value by remember { mutableStateOf("") }

        TextField(
            value = value,
            onValueChange = { value = it },
            textStyle = TextStyle.Default.copy(
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal
            ),
            placeholder = {
                Text(
                    text = stringResource(id = placeholder),
                    style = TextStyle.Default.copy(
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedPlaceholderColor = Mercury,
                unfocusedPlaceholderColor = Mercury,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedTextColor = Midnight,
                unfocusedTextColor = Midnight
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )

    }
}

@Preview
@Composable
fun CalculateScreenPreview() {
    CalculateScreen()
}

@Preview
@Composable
fun DestinationCardPreview() {
    DestinationCard()
}

@Preview(showBackground = true, backgroundColor = 0xFF7D7B7F)
@Composable
fun CalculateFieldItemPreview() {
    CalculateFieldItem(R.drawable.ic_sender, R.string.sender_location)
}