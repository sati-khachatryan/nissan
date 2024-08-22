package com.satenikkhachatryan.nissancontroller.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.satenikkhachatryan.nissancontroller.homescreen.HomeScreen

fun NavGraphBuilder.homeScreen() {
    composable(BottomNavigationTabs.Home.route) {
        HomeScreen()
    }
}