package de.osca.android.essentials.presentation.component.design

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R
import de.osca.android.essentials.utils.extensions.startDialIntent

@Composable
fun OpenPhoneElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    phone: String = "",
    context: Context,
    withTitle: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (withTitle) {
            Text(
                text = stringResource(id = R.string.global_phone_title),
                style = masterDesignArgs.normalTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                modifier = Modifier
                    .width(75.dp)
            )
        }
        Text(
            text = phone,
            style = masterDesignArgs.normalTextStyle,
            color = masterDesignArgs.highlightColor,
            modifier = Modifier
                .clickable {
                    context.startDialIntent(phone)
                }
        )
    }
}