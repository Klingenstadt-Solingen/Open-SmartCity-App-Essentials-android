package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R

@Composable
fun BaseTimePickerItem(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 0.dp, horizontal = 4.dp)
            .background(moduleDesignArgs.mDialogsBackColor ?: masterDesignArgs.mDialogsBackColor, RoundedCornerShape(6.dp))
            .clickable {
                onClick()
            }
            .requiredSize(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_clock_light),
            contentDescription = stringResource(id = R.string.global_select_time),
            tint = moduleDesignArgs.mDialogsTextColor ?: masterDesignArgs.mDialogsTextColor,
            modifier = Modifier
                .requiredSize(25.dp)
        )
    }
}