package de.osca.android.essentials.presentation.component.design

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.osca.android.essentials.R
import de.osca.android.essentials.utils.extensions.startWebIntent

@Composable
fun OpenWebsiteElement(
    masterDesignArgs: MasterDesignArgs,
    moduleDesignArgs: ModuleDesignArgs,
    url: String = "",
    withTitle: Boolean = true,
    context: Context
) {
    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        if(withTitle) {
            Text(
                text = stringResource(id = R.string.global_website_title),
                style = masterDesignArgs.normalTextStyle,
                color = moduleDesignArgs.mCardTextColor ?: masterDesignArgs.mCardTextColor,
                modifier = Modifier
                    .width(75.dp)
            )
        }
        Text(
            text = url,
            style = masterDesignArgs.normalTextStyle,
            color = masterDesignArgs.highlightColor,
            modifier = Modifier
                .clickable {
                    val fullUrl = if(url.startsWith("http") || url.startsWith("https")) url else "https://$url"
                    context.startWebIntent(fullUrl)
                }
        )
    }
}