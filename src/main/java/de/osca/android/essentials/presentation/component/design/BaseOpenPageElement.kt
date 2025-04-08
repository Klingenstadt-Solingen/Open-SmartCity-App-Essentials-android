package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R

@Composable
fun BaseOpenPageElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    text: String,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            style = masterDesignArgs.bodyTextStyle,
            color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp),
            tint = masterDesignArgs.highlightColor
        )
    }
}