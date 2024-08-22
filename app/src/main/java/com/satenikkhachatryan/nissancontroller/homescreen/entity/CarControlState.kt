package com.satenikkhachatryan.nissancontroller.homescreen.entity

data class CarControlState(
    val carControl: CarControl,
    var isEnabled: Boolean = false,
    var isLoading: Boolean = false
)
