package de.osca.android.essentials.presentation.component.web_view

sealed class WebViewState {
    object WaitingInitialization : WebViewState()
    class DisplayPage(val url: String) : WebViewState()
    class DisplayHtmlContent(val data: String) : WebViewState()
}