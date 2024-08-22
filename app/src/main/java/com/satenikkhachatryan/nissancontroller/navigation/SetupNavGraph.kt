package com.satenikkhachatryan.nissancontroller.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(rootController: NavHostController, startDestination: String) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = rootController,
        startDestination = startDestination
    ) {
        tabsScreen()
    }
}