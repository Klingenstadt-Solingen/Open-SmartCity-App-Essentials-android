package de.osca.android.essentials.presentation.component.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import de.osca.android.essentials.R
import java.time.LocalTime

@Composable
fun MainTimePickerDialog(
    masterDesignArgs: MasterDesignArgs,
    timePickerState: MaterialDialogState,
    onTimePicked: (LocalTime) -> Unit
) {
    MaterialDialog(
        dialogState = timePickerState,
        buttons = {
            positiveButton(
                stringResource(id = R.string.global_ok),
                textStyle = masterDesignArgs.buttonTextStyle
            )
            negativeButton(
                stringResource(id = R.string.global_cancel),
                textStyle = masterDesignArgs.buttonTextStyle
            )
        }
    ) {
        timepicker(
            title = stringResource(R.string.global_select_time),
            colors = masterDesignArgs.getTimePickerColors(),
            waitForPositiveButton = true,
            is24HourClock = true,
        ) { time ->
            onTimePicked.invoke(time)
        }
    }
}