package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateTimePickerElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    dateTimeString: String,
    onClickDate: () -> Unit,
    onClickTime: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        SpecialLabelText(
            text = dateTimeString,
            modifier = Modifier
                .weight(.8f),
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs
        )

        BaseDatePickerItem(
            onClick = onClickDate,
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs
        )

        BaseTimePickerItem(
            onClick = onClickTime,
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs
        )
    }
}