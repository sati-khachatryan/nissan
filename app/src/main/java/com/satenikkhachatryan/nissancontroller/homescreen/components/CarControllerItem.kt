package com.satenikkhachatryan.nissancontroller.homescreen.components


import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControl
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControlState


@Composable
fun CarControllerItem(carControlState: CarControlState, onClick: () -> Unit = {}) {
    val modifier = if (carControlState.isEnabled) Modifier.clickable(onClick = onClick) else Modifier
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .border(
                        width = 2.dp,
                        color = if (carControlState.isEnabled) Color.Black else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
            )
            if (carControlState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.inversePrimary
                )
            }
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.Center),
                painter = rememberVectorPainter(image = carControlState.carControl.icon),
                tint = if (carControlState.isEnabled) Color.Black else MaterialTheme.colorScheme.outline,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = carControlState.carControl.name,
            color = if (carControlState.isEnabled) Color.Black else MaterialTheme.colorScheme.outline,
        )
    }
}

@Preview
@Composable
fun CarControllerItemPreview() {
    CarControllerItem(CarControlState(CarControl.Lights)) {}
}