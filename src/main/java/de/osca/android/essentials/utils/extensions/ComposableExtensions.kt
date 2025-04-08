package de.osca.android.essentials.utils.extensions

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import de.osca.android.essentials.domain.entity.navigation.NavigationItem

data class NavAnimations constructor(
    val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    val exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    val popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition
)

fun NavGraphBuilder.composableForNav(
    navigationItem: NavigationItem,
    animations: NavAnimations? = null,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
    this.composable(
        route = navigationItem.route,
        arguments = navigationItem.arguments,
        deepLinks = navigationItem.deepLinks,
        content = content,
        enterTransition = animations?.enterTransition,
        exitTransition = animations?.exitTransition,
        popEnterTransition = animations?.popEnterTransition,
        popExitTransition = animations?.popExitTransition
    )
}

@Composable
fun colorFromHex(hexString: String): Color? {
    return if (hexString.isHexadecimal())
        return Color(android.graphics.Color.parseColor(hexString))
    else null
}

@Composable
fun colorFromHex(hexString: String, defaultColor: Color): Color {
    var colHex = hexString
    if(!colHex.startsWith("#")) { colHex = "#$colHex"
    }
    return colorFromHex(colHex) ?: defaultColor
}

@Composable
fun ShortToast(text: String) = Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()

@Composable
fun LongToast(text: String) = Toast.makeText(LocalContext.current, text, Toast.LENGTH_LONG).show()

@Composable
fun SetSystemStatusBar(isDarkText: Boolean, backgroundColor: Color) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        backgroundColor,
        darkIcons = isDarkText
    )
}