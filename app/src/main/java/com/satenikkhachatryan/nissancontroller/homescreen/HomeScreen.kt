package com.satenikkhachatryan.nissancontroller.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Headphones
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satenikkhachatryan.nissancontroller.R
import com.satenikkhachatryan.nissancontroller.homescreen.components.CarControllerItem
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControl
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControlState
import com.satenikkhachatryan.nissancontroller.homescreen.viewmodel.CarControllerViewModel
import com.satenikkhachatryan.nissancontroller.ui.AlertDialog
import com.satenikkhachatryan.nissancontroller.util.EventBus.eventFlow
import com.satenikkhachatryan.nissancontroller.util.HomeEvent
import com.satenikkhachatryan.nissancontroller.util.ObserveAsEvents
import com.satenikkhachatryan.nissancontroller.util.vmFactory
import kotlinx.coroutines.launch

@Composable
internal fun HomeScreen(
    controllerViewModel: CarControllerViewModel = viewModel<CarControllerViewModel>(factory = vmFactory { CarControllerViewModel() })
) {
    val carControlStateList by controllerViewModel.carControlsState.collectAsStateWithLifecycle()
    val onRemoteUnlockConfirmedLambda by rememberUpdatedState { carControlState: CarControlState ->
        controllerViewModel.onRemoteUnlockConfirmed(carControlState)
    }
    HomeContent(carControlStateList = carControlStateList, onRemoteUnlockConfirmedLambda)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeContent(
    carControlStateList: List<CarControlState>,
    onRemoteUnlockConfirmedLambda: (CarControlState) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var clickedButton by remember { mutableStateOf(CarControlState(CarControl.None)) }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 84.dp),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            painter = painterResource(id = R.drawable.nissan),
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            TopSection()
            CarInfoSection()
            Spacer(modifier = Modifier.height(240.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                carControlStateList.forEach { carControlState ->
                    when (carControlState.carControl) {
                        is CarControl.Lock -> {
                            CarControllerItem(carControlState = carControlState) {
                                onRemoteUnlockConfirmedLambda(carControlState)
                            }
                        }

                        CarControl.Unlock -> {
                            if (carControlState.isEnabled) {
                                CarControllerItem(carControlState = carControlState) {
                                    showDialog = true
                                    clickedButton = carControlState
                                }
                            } else {
                                CarControllerItem(carControlState = carControlState)
                            }
                        }

                        else -> CarControllerItem(carControlState = carControlState) {}
                    }
                }
            }
        }
    }
    AlertDialog(showDialog = showDialog, clickedButton = clickedButton, onRemoteUnlockConfirmedLambda) {
        showDialog = false
    }
    ObserveAsEvents(flow = eventFlow) { event ->
        when (event) {
            is HomeEvent.CarUnlocked -> {
                scope.launch {
                    snackbarHostState.showSnackbar(CAR_UNLOCK_COMPLETE_MESSAGE)
                }
            }

            is HomeEvent.UnlockingCar -> {
                scope.launch {
                    snackbarHostState.showSnackbar(CAR_UNLOCK_START_MESSAGE)
                }
            }
        }
    }

}

@Composable
private fun TopSection() {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = rememberVectorPainter(image = Icons.Outlined.Headphones),
            contentDescription = null
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                contentDescription = null
            )
            Text(text = stringResource(id = R.string.my_nissan_ariya))
        }
        Icon(
            modifier = Modifier.size(40.dp),
            painter = rememberVectorPainter(image = Icons.Outlined.Notifications),
            contentDescription = null
        )
    }
}

@Composable
private fun CarInfoSection() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = stringResource(id = R.string.est_range),
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.outline,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                modifier = Modifier.alignByBaseline(),
                text = stringResource(id = R.string.number_120),
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = Modifier
                    .alignByBaseline()
                    .padding(start = 8.dp),
                text = stringResource(id = R.string.mi),
                fontSize = 32.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(12.dp),
                painter = rememberVectorPainter(image = Icons.Outlined.Cloud),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                modifier = Modifier.alignByBaseline(),
                text = stringResource(id = R.string.celsius),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium
            )
            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .size(2.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape
                    )
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(id = R.string.city),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

const val CAR_UNLOCK_COMPLETE_MESSAGE = "Ariya unlocked."
const val CAR_UNLOCK_START_MESSAGE = "Waking Ariya to unlock..."

@Preview
@Composable
fun HomeScreenPreview() {
    HomeContent(
        listOf(
            CarControlState(CarControl.Unlock),
            CarControlState(CarControl.Lock),
        )
    ) {}
}