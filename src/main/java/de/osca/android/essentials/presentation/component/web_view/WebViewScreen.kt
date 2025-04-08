package de.osca.android.essentials.presentation.component.web_view

import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapper
import de.osca.android.essentials.presentation.component.topbar.ScreenTopBar
import de.osca.android.essentials.utils.constants.CONTENT_TYPE_HTML
import de.osca.android.essentials.utils.constants.UNICODE_UTF8
import de.osca.android.essentials.utils.extensions.shareText
import de.osca.android.essentials.R
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.utils.extensions.SetSystemStatusBar
import de.osca.android.essentials.utils.extensions.startWebIntent

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    navController: NavController,
    title: String? = null,
    url: String? = "",
    @StringRes cityName: Int = R.string.city_name,
    hasShareIcon: Boolean = false,
    webViewViewModel: WebViewViewModel = hiltViewModel(),
    behaviorArgs: WebViewBehaviorArgs = WebViewBehaviorArgs(),
    masterDesignArgs: MasterDesignArgs = webViewViewModel.defaultDesignArgs,
) {
    val context = LocalContext.current
    val design = webViewViewModel.webViewDesignArgs

    val webViewState = remember { webViewViewModel.webViewData }
    val shareEvent = remember { mutableStateOf(false) }

    webViewViewModel.initializeWebPage(url)

    if (shareEvent.value) {
        shareEvent(
            context = context,
            title = title.toString(),
            cityName = cityName,
            shareEvent = shareEvent
        )
    }

    SetSystemStatusBar(
        !(design.mIsStatusBarWhite ?: masterDesignArgs.mIsStatusBarWhite), Color.Transparent
    )

    ScreenWrapper(
        topBar = {
            ScreenTopBar(
                title = title,
                navController = navController,
                overrideTextColor = design.mTopBarTextColor,
                overrideBackgroundColor = design.mTopBarBackColor,
                actions = {
                    if(hasShareIcon) {
                        IconButton(
                            onClick = {
                                shareEvent.value = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                tint = design.mTopBarTextColor ?: masterDesignArgs.mTopBarTextColor,
                                contentDescription = "share"
                            )
                        }
                    }

                    if(url != null) {
                        IconButton(
                            onClick = {
                                context.startWebIntent(url)
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_external_link),
                                tint = design.mTopBarTextColor ?: masterDesignArgs.mTopBarTextColor,
                                contentDescription = "openInIntent"
                            )
                        }
                    }
                },
                masterDesignArgs = masterDesignArgs
            )
        },
        navController = navController,
        screenWrapperState = webViewViewModel.wrapperState,
        retryAction = {
            webViewViewModel.initializeWebPage(url)
        },
        masterDesignArgs = masterDesignArgs,
        moduleDesignArgs = design
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
        ) {
            Column {
                AndroidView(
                    factory = {
                        WebView(it).apply {
                            this.webViewClient = object : WebViewClient() {
                                override fun shouldOverrideUrlLoading(
                                    view: WebView?,
                                    request: WebResourceRequest?
                                ): Boolean {
                                    return false
                                }

                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)

                                    webViewViewModel.onContentReady()
                                }
                            }

                            this.webChromeClient = object : WebChromeClient(){
                                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                    if(newProgress == 100){
                                        webViewViewModel.onContentReady()
                                    }
                                }
                            }

                            settings.domStorageEnabled = behaviorArgs.isDomStorageEnabled
                            settings.javaScriptEnabled = behaviorArgs.isJavascriptEnabled
                        }
                    }, update = {
                        when (val state = webViewState.value) {
                            is WebViewState.DisplayPage -> {
                                if (it.url != state.url) {
                                    it.loadUrl(state.url)
                                }
                            }
                            is WebViewState.DisplayHtmlContent -> it.loadDataWithBaseURL(
                                null,
                                state.data,
                                CONTENT_TYPE_HTML,
                                UNICODE_UTF8,
                                null
                            )
                            else -> {}
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun shareEvent(
    context: Context,
    title: String = "",
    @StringRes cityName: Int = R.string.city_name,
    shareEvent: MutableState<Boolean>
) {
    context.shareText(
        title = title,
        text = "${title}\n\n${
            stringResource(
                id = R.string.global_shared_with,
                stringResource(id = cityName)
            )
        }"
    )
    shareEvent.value = false
}