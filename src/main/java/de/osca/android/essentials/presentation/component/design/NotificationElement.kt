package de.osca.android.essentials.presentation.component.design

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun NotificationElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    @StringRes notificationText: Int,
    initialState: Boolean = false,
    infoText: String? = null,
    onSwitch: (checked: Boolean) -> Unit,
    available: Boolean = true,
    notAvailableText: String? = null
) {
    val context = LocalContext.current
    val text = stringResource(id = notificationText)

    SimpleSpacedList(
        masterDesignArgs = masterDesignArgs,
        overrideSpace = 8.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(if(available) 1.0f else 0.5f)
        ) {
            Column {
                Text(
                    text = text,
                    style = masterDesignArgs.bodyTextStyle,
                    color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,

                )
                if(!available){
                    Text(
                        text = notAvailableText ?: "",
                        style = masterDesignArgs.bodyTextStyle,
                        color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    )
                }
            }


            MainSwitch(
                initialState = initialState,
                onSwitch = {
                    onSwitch(it)
                },
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs
            )
        }

        if (infoText != null) {
            Text(
                text = infoText,
                style = masterDesignArgs.subtitleTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
            )
        }
    }
}