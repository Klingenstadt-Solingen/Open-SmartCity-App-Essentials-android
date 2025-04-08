package de.osca.android.essentials.presentation.component.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import de.osca.android.essentials.R
import java.time.LocalDate

@Composable
fun MainDatePickerDialog(
    masterDesignArgs: MasterDesignArgs,
    yearRange: IntRange,
    datePickerState: MaterialDialogState,
    onDatePicked: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = datePickerState,
        buttons = {
            positiveButton(
                text = stringResource(id = R.string.global_ok),
                textStyle = masterDesignArgs.buttonTextStyle
            )
            negativeButton(
                text = stringResource(id = R.string.global_cancel),
                textStyle = masterDesignArgs.buttonTextStyle
            )
        }
    ) {
        datepicker(
            title = stringResource(R.string.global_select_time),
            yearRange = yearRange,
            colors = masterDesignArgs.getDatePickerColors()
        ) { date ->
            onDatePicked.invoke(date)
        }
    }
}