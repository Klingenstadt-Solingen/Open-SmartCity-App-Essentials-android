package de.osca.android.essentials.utils.extensions

import android.os.Parcelable
import androidx.compose.runtime.MutableState
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import de.osca.android.essentials.domain.entity.Coordinates
import de.osca.android.essentials.domain.entity.navigation.NavigationItem
import de.osca.android.essentials.presentation.component.screen_wrapper.ScreenWrapperState
import java.lang.Exception
import java.text.DecimalFormat
import java.util.*

infix fun <T> Boolean.then(element: T): T? = if (this) element else null

infix fun Boolean.thenDo(function: () -> Unit) {
    if (this) function.invoke()
}

fun String?.fromHtml(): String {
    return HtmlCompat.fromHtml(this ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

fun <T> MutableList<T>.addIfNotContains(element: T) {
    if (!this.contains(element = element)) {
        this.add(element)
    }

}

fun LatLng.toCoordinates(): Coordinates {
    return Coordinates(latitude = latitude, longitude = longitude)
}

fun MutableState<ScreenWrapperState>.displayContent() {
    this.value = ScreenWrapperState.DisplayContent
}

fun MutableState<ScreenWrapperState>.loading() {
    this.value = ScreenWrapperState.Loading
}

fun MutableState<ScreenWrapperState>.failure(msg: String?) {
    this.value = ScreenWrapperState.Failure(msg)
}

fun MutableState<ScreenWrapperState>.waitingInitialization() {
    this.value = ScreenWrapperState.WaitingInitialization
}

fun <K, V> HashMap<K, V>.getKey(target: V): K? {
    return filter { target == it.value }.keys.firstOrNull()
}

fun <T> MutableList<T>.resetWith(elements: Collection<T>?) {
    this.clear()
    this.addAll(elements ?: emptyList())
}

fun <T1, T2, R> safeLet(p1: T1?, p2: T2?, block: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) block(p1, p2) else null
}

fun <T1, T2, T3, R> safeLet(p1: T1?, p2: T2?, p3: T3?, block: (T1, T2, T3) -> R?): R? {
    return if (p1 != null && p2 != null && p3 != null) block(p1, p2, p3) else null
}

fun <T1, T2, T3, T4, R> safeLet(
    p1: T1?,
    p2: T2?,
    p3: T3?,
    p4: T4?,
    block: (T1, T2, T3, T4) -> R?
): R? {
    return if (p1 != null && p2 != null && p3 != null && p4 != null) block(p1, p2, p3, p4) else null
}

fun Number.roundUp(step: Int = 1): Int {
    return this.toInt() + (step - this.toInt() % step)
}

fun Number.isDecimal(): Boolean {
    return when (val n = this) {
        is Float -> n % 1 > 0
        is Double -> n % 1 > 0
        else -> n.toDouble() % 1 > 0
    }
}

fun Float.toFormattedString(decimalDigits: Int = 2): String {
    val numberFormatString = "###,###"
    val format = when (decimalDigits > 0) {
        true -> "$numberFormatString.${"#".repeat(decimalDigits)}"
        false -> numberFormatString
    }
    return try {
        DecimalFormat(format).format(this)
    } catch (e: Exception) {
        ""
    }
}

fun <T> Iterable<T>.safeTake(n: Int): List<T> {
    return when (this.count() >= n) {
        true -> take(n)
        else -> take(this.count())
    }
}

fun String.isHexadecimal(): Boolean {
    val hexadecimal = when (this.startsWith("#")) {
        true -> this.drop(1)
        false -> this
    }

    return try {
        Integer.parseInt(hexadecimal, 16)
        true
    } catch (e: NumberFormatException) {
        false
    }
}


fun NavController.setOnCurrentState(key: String, value: Parcelable): NavController {
    return this.apply {
        currentBackStackEntry?.savedStateHandle?.set(key, value)
    }
}

fun <T> NavController.getFromPreviousState(key: String): T? {
    return this.previousBackStackEntry?.savedStateHandle?.get<T>(key)
}

fun NavController.navigate(navItem: NavigationItem) {
    this.navigate(navItem.route)
}

fun getBounds(points: List<Coordinates>, defaultLatLng: LatLng): CameraUpdate {
    if (points.isEmpty()) {
        return CameraUpdateFactory.newLatLngZoom(defaultLatLng, 12.0f)
    }

    val builder = LatLngBounds.Builder()
    for (point in points) {
        builder.include(point.toLatLng())
    }
    val bounds = builder.build()

    return CameraUpdateFactory.newLatLngBounds(bounds, 100)
}
