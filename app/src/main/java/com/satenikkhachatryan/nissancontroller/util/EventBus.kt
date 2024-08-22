package com.satenikkhachatryan.nissancontroller.util

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object EventBus {
    private val _eventChannel = Channel<HomeEvent>(capacity = Channel.BUFFERED)
    val eventFlow = _eventChannel.receiveAsFlow()

    suspend fun sendEvent(event: HomeEvent) {
        _eventChannel.send(event)
    }
}

sealed interface HomeEvent {
    data class UnlockingCar(val message: String = "Waking Ariya to unlock...") : HomeEvent
    data class CarUnlocked(val message: String = "Ariya unlocked.") : HomeEvent
}