package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import de.osca.android.essentials.R

@Composable
fun BaseDataPrivacyElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    initialState: Boolean = false,
    onSwitch: (checked: Boolean) -> Unit,
    onClickDataPrivacy: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        MainSwitch(
            initialState = initialState,
            onSwitch = onSwitch,
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs
        )

        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.privacy_text_before))
                withStyle(style = SpanStyle(color = masterDesignArgs.highlightColor)) {
                    append(stringResource(id = R.string.privacy_text_clickable))
                }
                append(stringResource(id = R.string.privacy_text_after))
            },
            color = moduleDesignArgs.mHintTextColor ?: masterDesignArgs.mHintTextColor,
            style = masterDesignArgs.subtitleTextStyle,
            modifier = Modifier
                .clickable {
                    onClickDataPrivacy()
                }
        )
    }
}