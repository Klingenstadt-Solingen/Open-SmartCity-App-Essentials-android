package de.osca.android.essentials.presentation.component.bottom_navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import de.osca.android.essentials.domain.entity.navigation.NavigationItem
import de.osca.android.essentials.presentation.MainActivitySharedViewModel
import de.osca.android.essentials.presentation.component.design.MasterDesignArgs
import de.osca.android.essentials.utils.extensions.safeTake

@Composable
fun OSCABottomNavigation(
    masterDesignArgs: MasterDesignArgs,
    navController: NavController,
    sharedViewModel: MainActivitySharedViewModel
) {
    val context = LocalContext.current
    val items = sharedViewModel.bottomNavigationDesignArgs.items
    val itemLimit = 5

    val showMoreItems = remember { mutableStateOf<MutableMap.MutableEntry<NavigationItem, Pair<Int?, String?>>?>(null) }
    val itemsInDropDown = if(items.entries.size >= itemLimit) items.entries.toList().subList(itemLimit - 1, (itemLimit - 1) + (items.entries.size - (itemLimit - 1))) else emptyList()

    fun getCurrentRoute(): String? {
        return navController.currentBackStackEntry?.destination?.route
    }

    fun navigateTo(item: NavigationItem) {
        navController.navigate(item.route) {
            popUpTo(getCurrentRoute() ?: item.route) {
                inclusive = false
            }
            launchSingleTop = true
        }
    }

    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        BottomNavigation(
            backgroundColor = sharedViewModel.bottomNavigationDesignArgs.backgroundColor,
            contentColor = sharedViewModel.bottomNavigationDesignArgs.contentColor,
            elevation = sharedViewModel.bottomNavigationDesignArgs.elevation,
        ) {
            items.entries.safeTake(itemLimit).forEachIndexed { index, item ->
                BottomNavigationItem(
                    selected = sharedViewModel.selectedItem.value == item.key,
                    selectedContentColor = sharedViewModel.bottomNavigationDesignArgs.selectedColor,
                    unselectedContentColor = sharedViewModel.bottomNavigationDesignArgs.contentColor,
                    icon = {
                        if (items.entries.size > itemLimit && index >= itemLimit - 1) {
                            Box {
                                Icon( // more icon
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "",
                                    tint = sharedViewModel.bottomNavigationDesignArgs.contentColor,
                                    modifier = Modifier
                                        .size(24.dp)
                                )

                                DropdownMenu(
                                    expanded = showMoreItems.value != null,
                                    onDismissRequest = {
                                        showMoreItems.value = null
                                    },
                                    modifier = Modifier
                                        .background(sharedViewModel.bottomNavigationDesignArgs.backgroundColor)
                                ) {
                                    itemsInDropDown.forEach { elem ->
                                        DropdownMenuItem(
                                            onClick = {
                                                // open route
                                                if (getCurrentRoute() != elem.key.route) {
                                                    navigateTo(elem.key)
                                                }

                                                showMoreItems.value = null
                                            }
                                        ) {
                                            Icon( // nav icon
                                                painter = if (elem.value.second != null) rememberAsyncImagePainter(elem.value.second) else painterResource(id = elem.value.first ?: elem.key.icon),
                                                contentDescription = "",
                                                tint = if (sharedViewModel.selectedItem.value == elem.key)
                                                    sharedViewModel.bottomNavigationDesignArgs.selectedColor
                                                else
                                                    sharedViewModel.bottomNavigationDesignArgs.contentColor,
                                                modifier = Modifier
                                                    .size(24.dp)
                                            )

                                            Spacer(modifier = Modifier.width(16.dp))

                                            Text(
                                                text = (if (elem.key.title >= 0) context.getString(elem.key.title) else ""),
                                                style = masterDesignArgs.normalTextStyle,
                                                color = sharedViewModel.bottomNavigationDesignArgs.contentColor
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            Icon( // nav icon
                                painter = if (item.value.second != null) rememberAsyncImagePainter(item.value.second) else painterResource(id = item.value.first ?: item.key.icon),
                                contentDescription = "",
                                tint = if (sharedViewModel.selectedItem.value == item.key)
                                    sharedViewModel.bottomNavigationDesignArgs.selectedColor
                                else
                                    sharedViewModel.bottomNavigationDesignArgs.contentColor,
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                    },
                    label = {
                        if (sharedViewModel.bottomNavigationDesignArgs.showLabel) {
                            Text(
                                text = if (items.entries.size > itemLimit && index >= itemLimit - 1) "Mehr"
                                else
                                    (if (item.key.title >= 0) context.getString(item.key.title) else ""),
                                style = masterDesignArgs.subtitleTextStyle,
                                color = if (sharedViewModel.selectedItem.value == item.key)
                                    sharedViewModel.bottomNavigationDesignArgs.selectedColor
                                else
                                    sharedViewModel.bottomNavigationDesignArgs.contentColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    },
                    alwaysShowLabel = true,
                    onClick = {
                        if (items.entries.size > itemLimit && index >= itemLimit - 1) {
                            // open dropDown
                            showMoreItems.value = item
                        } else {
                            if (getCurrentRoute() != item.key.route) {
                                navController.navigate(item.key.route)
                            }
                        }
                    }
                )
            }
        }
    }
}