package de.osca.android.essentials.presentation.component.data_privacy

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs
import de.osca.android.essentials.R
import de.osca.android.essentials.presentation.component.design.BaseCardContainer
import de.osca.android.essentials.presentation.component.design.HtmlTextWebview
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.RootContainer
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar

@Composable
fun DataPrivacyScreen(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    navController: NavController,
    htmlPrivacyText: String?,
) {
    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = stringResource(id = R.string.global_data_privacy),
                navController = navController,
                masterDesignArgs = masterDesignArgs
            )
        },
        screenWrapperState = remember { mutableStateOf(ScreenWrapperState.DisplayContent) },
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = moduleDesignArgs
    ) {
        RootContainer(
            masterDesignArgs = masterDesignArgs,
            moduleDesignArgs = moduleDesignArgs
        ) {
            item {
                BaseCardContainer(
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = moduleDesignArgs
                ) {
                    HtmlTextWebview(
                        htmlString = htmlPrivacyText ?: ""
                    )
                }
            }
        }
    }
}