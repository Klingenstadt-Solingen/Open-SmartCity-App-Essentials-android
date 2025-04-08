package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MultipleButtonField(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    buttons: List<Pair<String, () -> Unit>> = emptyList()
) {
    Column(modifier = Modifier
        .padding(top = 16.dp)
    ) {
        Divider()

        buttons.forEach {  (text, action) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(50.dp)
                    .clickable {
                        action.invoke()
                    }
            ) {
                Text(
                    text = text.uppercase(),
                    textAlign = TextAlign.Center,
                    style = masterDesignArgs.bodyTextStyle,
                    color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Divider()
        }
    }
}