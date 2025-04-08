package de.osca.android.essentials.presentation.nav_items

import de.osca.android.essentials.R
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

class EssentialsNavItems {
    object DataPrivacyNavItem : NavigationItem(
        title = R.string.global_data_privacy,
        route = "data_privacy",
        icon = R.drawable.ic_circle
    )

    object WebViewNavItem : NavigationItem(
        title = R.string.global_webview_title,
        route = getWebViewRoute(),
        icon = R.drawable.ic_main_townhall
    )

    class DirectWebViewNavItem(
        url: String,
        displayedTitle: String? = null,
        share: Boolean = false
    ) : NavigationItem(
        title = R.string.global_webview_title,
        route = getWebViewRoute(url, displayedTitle, share),
        icon = R.drawable.ic_calendar_month
    )

    companion object {
        const val WEBVIEW_ROUTE = "web_view"
        const val ARG_WEB_VIEW_URL = "url"
        const val ARG_WEB_VIEW_TITLE = "title"
        const val ARG_WEB_VIEW_SHARE = "share"

        fun getWebViewRoute(url: String? = null, title: String? = "", share: Boolean = false): String {
            return if (url != null) {
                "$WEBVIEW_ROUTE?$ARG_WEB_VIEW_TITLE=$title?$ARG_WEB_VIEW_SHARE=$share?$ARG_WEB_VIEW_URL=$url"
            } else {
                "$WEBVIEW_ROUTE?$ARG_WEB_VIEW_TITLE={$ARG_WEB_VIEW_TITLE}?$ARG_WEB_VIEW_SHARE={$ARG_WEB_VIEW_SHARE}?$ARG_WEB_VIEW_URL={$ARG_WEB_VIEW_URL}"
            }
        }
    }
}