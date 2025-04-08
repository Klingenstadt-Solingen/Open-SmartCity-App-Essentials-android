package de.osca.android.essentials.presentation.component.design

import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun HtmlTextWebview(
    htmlString: String
) {
    val spannedText = remember {
        Html.fromHtml(
            htmlString,
            Html.FROM_HTML_MODE_LEGACY
        )
    }

    AndroidView(
        factory = {
            TextView(it).apply {
                autoLinkMask = Linkify.WEB_URLS + Linkify.EMAIL_ADDRESSES + Linkify.PHONE_NUMBERS
                linksClickable = true
                setBackgroundColor(Color.Transparent.toArgb())
                setTextColor(Color.Black.toArgb())
                setLinkTextColor(Color.Blue.toArgb())
                movementMethod = ScrollingMovementMethod();
            }
        },
        update = {
            it.text = spannedText
        }
    )
}
