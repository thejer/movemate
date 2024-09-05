package com.example.movemate.ui.nav

import androidx.annotation.DrawableRes
import com.example.movemate.R

sealed class Screen(val route: String, val title: String, @DrawableRes val icon: Int) {
    data object Home : Screen("home", "Home", R.drawable.ic_home)
    data object Calculate : Screen("calculate", "Calculate", R.drawable.ic_calculate)
    data object Shipment : Screen("shipment", "Shipment", R.drawable.ic_pending)
    data object Profile : Screen("profile", "Profile", R.drawable.ic_profile)
}
