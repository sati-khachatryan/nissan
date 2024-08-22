package com.satenikkhachatryan.nissancontroller.homescreen.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BatterySaver
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Light
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.ui.graphics.vector.ImageVector

sealed class CarControl(val icon: ImageVector, val name: String) {

    data object Lock : CarControl(icon = Icons.Outlined.Lock, name = CarControlName.LOCK.displayName)

    data object Unlock : CarControl(icon = Icons.Outlined.LockOpen, name = CarControlName.UNLOCK.displayName)

    data object Climate : CarControl(icon = Icons.Outlined.Cloud, name = CarControlName.CLIMATE.displayName)

    data object Charge : CarControl(icon = Icons.Outlined.BatterySaver, name = CarControlName.CHARGE.displayName)

    data object Lights : CarControl(icon = Icons.Outlined.Light, name = CarControlName.LIGHTS.displayName)

    data object None : CarControl(icon = Icons.Outlined.Close, name = CarControlName.NONE.displayName)
}

enum class CarControlName(val displayName: String) {
    LOCK("Lock"),
    UNLOCK("Unlock"),
    CLIMATE("Climate"),
    CHARGE("Charge"),
    LIGHTS("Lights"),
    NONE("None")
}