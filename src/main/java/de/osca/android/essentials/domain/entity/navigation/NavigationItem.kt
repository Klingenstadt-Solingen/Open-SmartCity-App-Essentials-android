package de.osca.android.essentials.domain.entity.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavDeepLink

open class NavigationItem(
    @StringRes
    val title: Int,
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val deepLinks: List<NavDeepLink> = emptyList(),
    @DrawableRes
    val icon: Int
)