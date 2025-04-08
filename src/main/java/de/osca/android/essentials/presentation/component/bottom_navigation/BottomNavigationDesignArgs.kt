package de.osca.android.essentials.presentation.component.bottom_navigation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

interface BottomNavigationDesignArgs {
    val backgroundColor: Color
    val contentColor: Color
    val selectedColor: Color
    val elevation: Dp
    val items: MutableMap<NavigationItem, Pair<Int?, String?>>
    val showLabel: Boolean
}