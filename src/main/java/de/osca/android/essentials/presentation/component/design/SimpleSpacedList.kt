package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SimpleSpacedList(
    masterDesignArgs: MasterDesignArgs,
    overrideSpace: Dp? = null,
    borderSpace: Dp = 0.dp,
    alignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(overrideSpace ?: masterDesignArgs.spaceList),
        horizontalAlignment = alignment,
        modifier = Modifier
            .fillMaxWidth()
            .padding(borderSpace)
    ){
        content()
    }
}