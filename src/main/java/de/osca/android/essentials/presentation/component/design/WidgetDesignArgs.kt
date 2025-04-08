package de.osca.android.essentials.presentation.component.design

import androidx.compose.ui.graphics.Color

interface WidgetDesignArgs {
    val mWidgetShowLessText: Int?
    val mWidgetShowMoreText: Int?
    val mWidgetHeaderTextColor: Color?
    val mWidgetShowMoreTextColor: Color?
    val vWidgetShowMoreOption: Boolean
    val vIsWidgetVisible: Boolean
    val vWidgetTitle: Int
}