package de.osca.android.essentials.presentation.component.design

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WidgetContainer(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    allowEditing: Boolean = true,
    canUp: Boolean = true,
    canDown: Boolean = true,
    moveUp: (showControls: MutableState<Boolean>) -> Unit,
    moveDown: (showControls: MutableState<Boolean>) -> Unit,
    content: @Composable (isEditing: Boolean) -> Unit
) {
    val showControls = remember {
        mutableStateOf(false)
    }

    if(allowEditing) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    showControls.value = false
                },
                onDoubleClick = { },
                onLongClick = {
                    showControls.value = true
                }
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showControls.value) {
                    if (canUp) {
                        MainButton(
                            onClick = { moveUp(showControls) },
                            buttonText = "nach oben",
                            modifier = Modifier
                                .padding(top = 0.dp)
                                .width(200.dp),
                            masterDesignArgs = masterDesignArgs,
                            moduleDesignArgs = moduleDesignArgs
                        )
                    }
                }

                content(showControls.value)

                if (showControls.value) {
                    if (canDown) {
                        MainButton(
                            onClick = { moveDown(showControls) },
                            buttonText = "nach unten",
                            modifier = Modifier
                                .padding(top = 0.dp)
                                .width(200.dp),
                            masterDesignArgs = masterDesignArgs,
                            moduleDesignArgs = moduleDesignArgs
                        )
                    }

                    Text(
                        text = "abbrechen",
                        style = masterDesignArgs.bodyTextStyle,
                        color = masterDesignArgs.mCardTextColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(masterDesignArgs.errorTextColor, RoundedCornerShape(50))
                            .padding(2.dp)
                            .clickable {
                                showControls.value = false
                            },
                    )
                }
            }
        }
    } else {
        content(showControls.value)
    }
}