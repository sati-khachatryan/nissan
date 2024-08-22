package com.satenikkhachatryan.nissancontroller.util


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun ViewModel.sendEvent(event: HomeEvent) {
    viewModelScope.launch {
        EventBus.sendEvent(event)
    }
}