package com.satenikkhachatryan.nissancontroller.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.satenikkhachatryan.nissancontroller.homescreen.components.TabsScreen

fun NavGraphBuilder.tabsScreen() {
    composable(TABS_SCREEN_ROUTE) {
        TabsScreen()
    }
}

const val TABS_SCREEN_ROUTE = "tabs_screen"