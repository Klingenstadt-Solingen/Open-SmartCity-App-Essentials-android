package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.presentation.component.topbar.ResponsiveText

@Composable
fun MainButton(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    modifier: Modifier = Modifier
        .padding(top = 24.dp, bottom = 0.dp)
        .fillMaxWidth(),
    onClick: () -> Unit,
    buttonText: String,
    enabled: Boolean = true
) {
    Button(
        shape = RoundedCornerShape(moduleDesignArgs.mShapeCard ?: masterDesignArgs.mShapeCard),
        elevation = masterDesignArgs.getButtonElevation(),
        enabled = enabled,
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = if(moduleDesignArgs.mButtonBackgroundColor != null) moduleDesignArgs.getButtonColors() else masterDesignArgs.getButtonColors()
    ) {
        ResponsiveText(
            text = buttonText.uppercase(),
            color = moduleDesignArgs.mDialogsTextColor ?: masterDesignArgs.mDialogsTextColor,
            textStyle = masterDesignArgs.overlineTextStyle,
            maxLines = 1
        )
    }
}
