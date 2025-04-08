package de.osca.android.essentials.presentation.component.screen_wrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.osca.android.essentials.R
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.presentation.component.design.ModuleDesignArgs
import de.osca.android.essentials.presentation.component.design.LoadingScreen
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.presentation.component.design.MessageBoxScreen
import de.osca.android.essentials.presentation.component.toast.ToastState
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.utils.extensions.LongToast
import de.osca.android.essentials.utils.extensions.ShortToast

@Composable
fun ScreenWrapper(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    title: String? = null,
    withTopBar: Boolean = true,
    navController: NavController? = null,
    topBar: (@Composable (NavController?) -> Unit)? = null,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    modifier: Modifier = Modifier.fillMaxSize(),
    viewModel: BaseViewModel? = null,
    screenWrapperState: MutableState<ScreenWrapperState> = getWrapperStateFor(viewModel),
    toastState: MutableState<ToastState> = getToastStateFor(viewModel),
    disableClearFocusOnTap: Boolean = false,
    overrideStatusBar: Boolean = false,
    developMenu: (() -> Unit)? = null,
    retryAction: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current
    val mTopBar = topBar ?: {
        ScreenTopBar(
            title = title,
            navController = navController,
            masterDesignArgs = masterDesignArgs
        )
    }

    when (val toast = toastState.value) {
        is ToastState.ShowLongToast -> LongToast(toast.message)
        is ToastState.ShowShortToast -> ShortToast(toast.message)
        is ToastState.NoToast -> {}
    }

    Column(
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = verticalArrangement,
        modifier = Modifier
            .pointerInput(Unit) {
                if (!disableClearFocusOnTap) {
                    detectTapGestures(onTap = {
                        localFocusManager.clearFocus()
                    })
                }
            }
            .background(moduleDesignArgs.mScreenBackColor ?: masterDesignArgs.mScreenBackColor)
            .then(modifier)
    ) {
        when (screenWrapperState.value) {
            is ScreenWrapperState.WaitingInitialization -> {

            }
            is ScreenWrapperState.Loading -> {
                LoadingScreen(
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = moduleDesignArgs
                )
            }
            is ScreenWrapperState.Failure -> {
                MessageBoxScreen(
                    message = screenWrapperState.value.msg,
                    masterDesignArgs = masterDesignArgs,
                    moduleDesignArgs = moduleDesignArgs,
                    action = retryAction,
                    actionName = stringResource(id = R.string.global_try_again)
                )
            }
            is ScreenWrapperState.DisplayContent -> {
                if (withTopBar) {
                    mTopBar(navController)
                } else {
                    if (overrideStatusBar) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .padding(end = 6.dp)
                        ) {
                            if (developMenu != null) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "toolbarMenu",
                                    tint = Color.hsl(0f, 0f, .95f),
                                    modifier = Modifier
                                        .size(15.dp)
                                        .clickable {
                                            developMenu()
                                        }
                                )
                            }
                        }
                    }
                }

                content()
            }
        }
    }
}


private fun getWrapperStateFor(viewModel: BaseViewModel?): MutableState<ScreenWrapperState> {
    return viewModel?.wrapperState ?: mutableStateOf(ScreenWrapperState.WaitingInitialization)
}

private fun getToastStateFor(viewModel: BaseViewModel?): MutableState<ToastState> {
    return viewModel?.toastState ?: mutableStateOf(ToastState.NoToast)
}