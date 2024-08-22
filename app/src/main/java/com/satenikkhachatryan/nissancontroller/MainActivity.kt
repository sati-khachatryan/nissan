package com.satenikkhachatryan.nissancontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.satenikkhachatryan.nissancontroller.homescreen.HomeScreen
import com.satenikkhachatryan.nissancontroller.navigation.SetupNavGraph
import com.satenikkhachatryan.nissancontroller.navigation.TABS_SCREEN_ROUTE
import com.satenikkhachatryan.nissancontroller.ui.theme.NissanControllerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NissanControllerTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    SetupNavGraph(rootController = navController, startDestination = TABS_SCREEN_ROUTE)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NissanControllerTheme {
        HomeScreen()
    }
}