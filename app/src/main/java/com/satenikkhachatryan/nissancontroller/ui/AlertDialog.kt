package com.satenikkhachatryan.nissancontroller.ui


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.satenikkhachatryan.nissancontroller.R
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControl
import com.satenikkhachatryan.nissancontroller.homescreen.entity.CarControlState
import kotlinx.coroutines.launch

@Composable
fun AlertDialog(
    showDialog: Boolean,
    clickedButton: CarControlState,
    onRemoteUnlockConfirmedLambda: suspend (CarControlState) -> Unit,
    onDismiss: () -> Unit
) {
    val scope = rememberCoroutineScope()
    if (showDialog) {
        androidx.compose.material3.AlertDialog(
            onDismissRequest = {
                onDismiss()
            },
            properties = DialogProperties(dismissOnClickOutside = false),
            title = {
                Text(text = stringResource(id = R.string.dialog_title))
            },
            text = {
                Text(text = stringResource(id = R.string.dialog_text))
            },
            confirmButton = {
                Button(
                    onClick = {
                        onDismiss()
                        scope.launch {
                            onRemoteUnlockConfirmedLambda(clickedButton)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Blue)
                ) {
                    Text(text = stringResource(id = R.string.dialog_confirmation))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(
                        text = stringResource(id = R.string.dialog_dismiss),
                        color = Color.Blue
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun AlertDialogPreview() {
    AlertDialog(
        showDialog = true,
        clickedButton = CarControlState(CarControl.Lock),
        onRemoteUnlockConfirmedLambda = {}) {

    }
}
