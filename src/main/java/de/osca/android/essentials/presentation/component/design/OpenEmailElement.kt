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
import de.osca.android.essentials.utils.extensions.emailTo

@Composable
fun OpenEmailElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    email: String = "",
    subject: String = stringResource(id = R.string.global_mail_subject),
    withTitle: Boolean = true,
    context: Context
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        if (withTitle) {
            Text(
                text = stringResource(id = R.string.global_mail_title),
                style = masterDesignArgs.normalTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                modifier = Modifier
                    .width(75.dp)
            )
        }
        Text(
            text = email,
            style = masterDesignArgs.normalTextStyle,
            color = masterDesignArgs.highlightColor,
            modifier = Modifier
                .clickable {
                    context.emailTo(email, subject)
                }
        )
    }
}