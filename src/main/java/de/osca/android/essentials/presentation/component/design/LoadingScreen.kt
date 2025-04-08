package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R

@Composable
fun LoadingScreen(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CircularProgressIndicator(
            strokeWidth = 2.dp,
            color = moduleDesignArgs.mDialogsBackColor ?: masterDesignArgs.mDialogsBackColor,
            modifier = Modifier
                .sizeIn(50.dp)
        )

        Spacer(modifier = Modifier
            .height(16.dp))

        Text(
            text = stringResource(id = R.string.global_loading),
            style = masterDesignArgs.normalTextStyle,
            color = moduleDesignArgs.mScreenTextColor ?: masterDesignArgs.mScreenTextColor
        )
    }
}