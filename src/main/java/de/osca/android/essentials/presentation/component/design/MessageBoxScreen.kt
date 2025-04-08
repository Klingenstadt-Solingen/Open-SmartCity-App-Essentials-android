package de.osca.android.essentials.presentation.component.design

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R
import androidx.navigation.NavController
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar

@Composable
fun MessageBoxScreen(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    @StringRes title: Int = -1,
    titleText: String = "",
    message: String? = null,
    allowGoBack: Boolean = true,
    navController: NavController? = null,
    action: (() -> Unit)? = null,
    actionName: String? = null
) {
    ScreenWrapper(
        title = if (title >= 0) { stringResource(id = title) } else { titleText },
        topBar = {
            if (allowGoBack) {
                ScreenTopBar(
                    title = if (title >= 0) stringResource(id = title) else titleText,
                    navController = navController,
                    masterDesignArgs = masterDesignArgs
                )
            }
        },
        screenWrapperState = remember { mutableStateOf(ScreenWrapperState.DisplayContent) },
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = moduleDesignArgs
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            BaseCardContainer(
                masterDesignArgs = masterDesignArgs,
                moduleDesignArgs = moduleDesignArgs,
                modifier = Modifier
                    .fillMaxWidth(.75f)
                    .wrapContentHeight()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    SimpleSpacedList(
                        masterDesignArgs = masterDesignArgs,
                        overrideSpace = 24.dp,
                        alignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.global_no_result),
                            style = masterDesignArgs.captionTextStyle.copy(
                                fontWeight = FontWeight.Black
                            ),
                            color = moduleDesignArgs.mCardTextColor
                                ?: masterDesignArgs.mCardTextColor,
                            maxLines = 1,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = message.toString(),
                            style = masterDesignArgs.normalTextStyle,
                            color = moduleDesignArgs.mCardTextColor
                                ?: masterDesignArgs.mCardTextColor,
                            maxLines = 20,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )

                        if (action != null) {
                            MainButton(
                                onClick = action,
                                buttonText = actionName ?: stringResource(id = R.string.global_try_again),
                                masterDesignArgs = masterDesignArgs,
                                moduleDesignArgs = moduleDesignArgs
                            )
                        }
                    }
                }
            }
        }
    }
}