package com.satenikkhachatryan.nissancontroller.homescreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.satenikkhachatryan.nissancontroller.navigation.BottomNavigationTabs
import com.satenikkhachatryan.nissancontroller.navigation.homeScreen
import com.satkhachatryan.nissantest.navigation.BottomBar


@Composable
fun TabsScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomBar(navController) })
    { paddingValues ->
        NavHost(
            modifier = Modifier
                .fillMaxSize(),
            navController = navController,
            startDestination = BottomNavigationTabs.Home.route
        ) {
            homeScreen()
            composable(BottomNavigationTabs.Vehicle.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(text = BottomNavigationTabs.Vehicle.name)
                }
            }

            composable(BottomNavigationTabs.Map.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(text = BottomNavigationTabs.Map.name)
                }
            }

            composable(BottomNavigationTabs.Support.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(text = BottomNavigationTabs.Support.name)
                }
            }

            composable(BottomNavigationTabs.Settings.route) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues), contentAlignment = Alignment.Center
                ) {
                    Text(text = BottomNavigationTabs.Settings.name)
                }
            }
        }
    }
}