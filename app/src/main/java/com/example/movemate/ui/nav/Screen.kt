package com.example.movemate.ui.nav

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.movemate.R

sealed class Screen(val route: String, @StringRes val title: Int, @DrawableRes val icon: Int) {
    data object Home : Screen("home", R.string.home, R.drawable.ic_home)
    data object Calculate : Screen("calculate", R.string.calculate, R.drawable.ic_calculate)
    data object Shipment : Screen("shipment", R.string.shipment, R.drawable.ic_pending)
    data object Profile : Screen("profile", R.string.profile, R.drawable.ic_profile)
}
