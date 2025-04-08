package de.osca.android.essentials.presentation.component.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import de.osca.android.essentials.R

@Composable
fun defaultNavigationIcon(navController: NavController): @Composable (() -> Unit)? {
    return if (navController.previousBackStackEntry != null) {
        {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.global_back)
                )
            }
        }
    } else null
}