package com.satkhachatryan.nissantest.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.satenikkhachatryan.nissancontroller.navigation.BottomNavigationTabs

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val tabBarItems: List<BottomNavigationTabs> =
        listOf(
            BottomNavigationTabs.Home,
            BottomNavigationTabs.Vehicle,
            BottomNavigationTabs.Map,
            BottomNavigationTabs.Support,
            BottomNavigationTabs.Settings
        )
    NavigationBar(
        modifier = Modifier
            .navigationBarsPadding()
            .height(80.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        tabBarItems.forEach { tabBarItem ->
            val isSelected = currentRoute == tabBarItem.route
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp, bottom = 16.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            if (isSelected) return@clickable
                            navController.navigate(tabBarItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (isSelected)
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        thickness = 4.dp,
                        color = Color.Blue
                    )

                Icon(
                    painter = rememberVectorPainter(image = tabBarItem.icon),
                    tint = if (isSelected) Color.Blue else MaterialTheme.colorScheme.outline,
                    contentDescription = null
                )
                Text(
                    text = tabBarItem.name,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Normal,
                    color = if (isSelected) Color.Blue else MaterialTheme.colorScheme.outline
                )
            }
        }
    }
}