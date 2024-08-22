package com.satenikkhachatryan.nissancontroller.homescreen.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControl
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControlState
import com.satenikkhachatryan.nissancontroller.util.HomeEvent
import com.satenikkhachatryan.nissancontroller.util.sendEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CarControllerViewModel() : ViewModel() {

    private val controllers: List<CarControlState> by lazy {
        val initialList: MutableList<CarControlState> = mutableListOf(
            CarControlState(CarControl.Lock, isEnabled = false, isLoading = false),
            CarControlState(CarControl.Unlock, isEnabled = true, isLoading = false),
            CarControlState(CarControl.Climate, isEnabled = true, isLoading = false),
            CarControlState(CarControl.Charge, isEnabled = true, isLoading = false),
            CarControlState(CarControl.Lights, isEnabled = true, isLoading = false)
        )
        initialList
    }

    private val _carControlsState = MutableStateFlow(controllers)
    val carControlsState = _carControlsState.asStateFlow()

    fun onRemoteUnlockConfirmed(carControlState: CarControlState) {
        viewModelScope.launch {
            var isUnlockedLoadingEnabled = false
            var isLockButtonEnabled = false
            var isUnlockedButtonEnabled = true
            var lockNetworkDelay = 0L
            _carControlsState.update {
                if (carControlState.carControl is CarControl.Lock) {
                    isUnlockedButtonEnabled = true
                    isLockButtonEnabled = false
                }
                if (carControlState.carControl is CarControl.Unlock) {
                    isUnlockedLoadingEnabled = true
                    isUnlockedButtonEnabled = false
                    isLockButtonEnabled = true
                    // Network delay
                    lockNetworkDelay = 4000
                    sendEvent(HomeEvent.UnlockingCar())
                }

                it.map { state ->
                    when (state.carControl) {
                        is CarControl.Lock -> CarControlState(state.carControl, false)
                        is CarControl.Unlock -> CarControlState(
                            state.carControl,
                            isUnlockedButtonEnabled,
                            isUnlockedLoadingEnabled
                        )

                        else -> CarControlState(state.carControl, true)
                    }
                }
            }
            // For test purposes only.
            delay(lockNetworkDelay)

            // Update data.
            _carControlsState.update {
                if (!isUnlockedButtonEnabled) {
                    sendEvent(HomeEvent.CarUnlocked())
                }
                it.map { state ->
                    when (state.carControl) {
                        is CarControl.Lock -> CarControlState(state.carControl, isLockButtonEnabled)
                        is CarControl.Unlock -> CarControlState(state.carControl, isUnlockedButtonEnabled)
                        else -> CarControlState(state.carControl, true)
                    }
                }
            }
        }
    }

}