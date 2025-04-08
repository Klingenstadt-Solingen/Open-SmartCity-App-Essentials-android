package de.osca.android.essentials.presentation.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

private const val TEXT_SCALE_REDUCTION_INTERVAL = 0.9f

@Composable
fun ScreenTopBar(
    masterDesignArgs: MasterDesignArgs,
    title: String? = null,
    overrideTextColor: Color? = null,
    overrideBackgroundColor: Color? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    navController: NavController? = null,
    modifier: Modifier = Modifier.padding(bottom = 6.dp)
) {
    val navIcon = when (navigationIcon == null && navController != null) {
        true -> defaultNavigationIcon(navController = navController)
        else -> navigationIcon
    }
    InsetAwareTopAppBar(
        title = {
            DefaultScreenTopBarTitle(
                title = title,
                overrideTextColor = overrideTextColor,
                masterDesignArgs = masterDesignArgs
            )
        },
        backgroundColor = overrideBackgroundColor ?: masterDesignArgs.mTopBarBackColor,
        navigationIcon = navIcon,
        actions = actions,
        contentColor = overrideTextColor ?: masterDesignArgs.mTopBarTextColor,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun DefaultScreenTopBarTitle(
    title: String?,
    overrideTextColor: Color?,
    masterDesignArgs: MasterDesignArgs
) {
    if (title != null) {
        ResponsiveText(
            text = title,
            color = overrideTextColor ?: masterDesignArgs.mTopBarTextColor,
            textStyle = masterDesignArgs.overlineTextStyle,
            maxLines = 2
        )
    }
}

@Composable
fun ResponsiveText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    textAlign: TextAlign = TextAlign.Start,
    textStyle: TextStyle,
    targetTextSizeHeight: TextUnit = textStyle.fontSize,
    maxLines: Int = 1,
) {
    var textSize by remember { mutableStateOf(targetTextSizeHeight) }

    Text(
        modifier = modifier,
        text = text,
        color = color,
        textAlign = textAlign,
        fontSize = textSize,
        fontFamily = textStyle.fontFamily,
        fontStyle = textStyle.fontStyle,
        fontWeight = textStyle.fontWeight,
        lineHeight = textStyle.lineHeight,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        onTextLayout = { textLayoutResult ->
            val maxCurrentLineIndex: Int = textLayoutResult.lineCount - 1

            if (textLayoutResult.isLineEllipsized(maxCurrentLineIndex)) {
                textSize = textSize.times(TEXT_SCALE_REDUCTION_INTERVAL)
            }
        },
    )
}