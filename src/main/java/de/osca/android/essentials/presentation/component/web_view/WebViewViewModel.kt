package de.osca.android.essentials.presentation.component.web_view

import androidx.compose.runtime.mutableStateOf
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.essentials.presentation.base.BaseViewModel
import de.osca.android.essentials.utils.extensions.displayContent
import de.osca.android.essentials.utils.extensions.loading
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
open class WebViewViewModel @Inject constructor(
    val webViewDesignArgs: WebViewDesignArgs
) : BaseViewModel() {
    val webViewData = mutableStateOf<WebViewState>(WebViewState.DisplayHtmlContent(""))

    val isContentReady = mutableStateOf(false)

    fun initializeWebPage(url: String?) {
        wrapperState.loading()

        loadUrl(url = url ?: "")
    }

    fun loadUrl(url: String): Job = launchDataLoad {
        webViewData.value = WebViewState.DisplayPage(url)

        onContentReady()
    }

    fun loadContent(data: String) {
        webViewData.value = WebViewState.DisplayHtmlContent(data)

        onContentReady()
    }

    fun onContentReady() {
        isContentReady.value = true
        wrapperState.displayContent()
    }
}