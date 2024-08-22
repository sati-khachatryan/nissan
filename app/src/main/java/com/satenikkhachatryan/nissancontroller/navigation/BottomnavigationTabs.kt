package com.satenikkhachatryan.nissancontroller.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DirectionsCar
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationTabs(val name: String, val icon: ImageVector, val route: String) {

    data object Home :
        BottomNavigationTabs(
            name = BottomTabs.HOME.displayName,
            icon = Icons.Outlined.Home,
            route = HOME_SCREEN
        )

    data object Vehicle : BottomNavigationTabs(
        name = BottomTabs.VEHICLE.displayName,
        icon = Icons.Outlined.DirectionsCar,
        route = VEHICLE_SCREEN
    )

    data object Map :
        BottomNavigationTabs(
            name = BottomTabs.MAP.displayName,
            icon = Icons.Outlined.Map,
            route = MAP_SCREEN
        )

    data object Support : BottomNavigationTabs(
        name = BottomTabs.SUPPORT.displayName,
        icon = Icons.Outlined.SupportAgent,
        route = SUPPORT_SCREEN
    )

    data object Settings : BottomNavigationTabs(
        name = BottomTabs.SETTINGS.displayName,
        icon = Icons.Outlined.Settings,
        route = SETTINGS_SCREEN
    )
}

enum class BottomTabs(val displayName: String) {
    HOME("Home"),
    VEHICLE("Vehicle"),
    MAP("Map"),
    SUPPORT("Support"),
    SETTINGS("Settings")
}

const val HOME_SCREEN = "home_screen"
const val VEHICLE_SCREEN = "vehicle_screen"
const val MAP_SCREEN = "map_screen"
const val SUPPORT_SCREEN = "support_screen"
const val SETTINGS_SCREEN = "settings_screen"