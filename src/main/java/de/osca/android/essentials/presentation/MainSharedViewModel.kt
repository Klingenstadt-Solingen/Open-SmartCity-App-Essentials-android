package de.osca.android.essentials.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.MutableState
import de.osca.android.essentials.domain.entity.navigation.NavigationItem
import de.osca.android.essentials.domain.entity.server.ParseInstallation
import de.osca.android.essentials.presentation.component.bottom_navigation.BottomNavigationDesignArgs

interface MainSharedViewModel {
    val topBarSize: MutableState<Int>
    val isBottomNavVisible: MutableState<Boolean>
    val defaultContentPaddings: MutableState<PaddingValues>
    val currentContentPaddings: MutableState<PaddingValues>
    val bottomNavDesign: MutableState<BottomNavigationDesignArgs?>
    val useSystemToolBar: MutableState<Boolean>
    val installation: MutableState<ParseInstallation?>
    val selectedItem: MutableState<NavigationItem?>

    fun setTopBarSize(newSize: Int)
    fun setContentPaddings(paddingValues: PaddingValues)
    fun setIsBottomNavVisible(isVisible: Boolean)
    fun setDefaultContentPadding(value: PaddingValues)
    fun setCurrentContentPadding(value: PaddingValues)
    fun setBottomNavDesign(designArgs: BottomNavigationDesignArgs)
}
