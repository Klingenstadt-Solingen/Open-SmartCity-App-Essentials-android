package de.osca.android.essentials.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import de.osca.android.essentials.domain.entity.navigation.NavigationItem
import de.osca.android.essentials.domain.entity.server.ParseInstallation
import de.osca.android.essentials.presentation.component.bottom_navigation.BottomNavigationDesignArgs
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainActivitySharedViewModel @Inject constructor(
    val bottomNavigationDesignArgs: BottomNavigationDesignArgs,
    val defaultDesignArgs: MasterDesignArgs
) : ViewModel(), MainSharedViewModel {
    override val topBarSize = mutableIntStateOf(0)
    override val isBottomNavVisible: MutableState<Boolean> = mutableStateOf(true)
    override val currentContentPaddings: MutableState<PaddingValues> =
        mutableStateOf(PaddingValues())
    override val defaultContentPaddings: MutableState<PaddingValues> =
        mutableStateOf(PaddingValues())
    override val bottomNavDesign = mutableStateOf<BottomNavigationDesignArgs?>(null)
    override val useSystemToolBar: MutableState<Boolean> = mutableStateOf(false)

    override val installation: MutableState<ParseInstallation?> = mutableStateOf(null)

    override val selectedItem = mutableStateOf<NavigationItem?>(null)

    override fun setTopBarSize(newSize: Int) {
        val current = currentContentPaddings.value.calculateTopPadding()
        val additional = newSize.dp - current
        topBarSize.intValue = additional.value.roundToInt()
    }

    override fun setContentPaddings(paddingValues: PaddingValues) {
        currentContentPaddings.value = paddingValues
    }

    override fun setIsBottomNavVisible(isVisible: Boolean) {
        isBottomNavVisible.value = isVisible
    }

    override fun setBottomNavDesign(designArgs: BottomNavigationDesignArgs) {
        bottomNavDesign.value = designArgs
    }

    override fun setDefaultContentPadding(value: PaddingValues) {
        defaultContentPaddings.value = value
    }

    override fun setCurrentContentPadding(value: PaddingValues) {
        currentContentPaddings.value = value
    }

    @Composable
    fun resetBottomNavBar(
        visibility: Boolean = true
    ) {
        setBottomNavDesign(bottomNavigationDesignArgs)

        setIsBottomNavVisible(visibility)
    }

    @Composable
    fun setSelectedTabbarNavItem(navigationItem: NavigationItem?) {
        selectedItem.value = navigationItem
    }
}