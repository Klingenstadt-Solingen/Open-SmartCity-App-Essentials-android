package de.osca.android.essentials.utils.extensions

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import de.osca.android.essentials.R
import de.osca.android.essentials.domain.entity.BooleanLiteralType
import de.osca.android.essentials.domain.entity.Coordinates
import de.osca.android.essentials.utils.constants.CONTENT_TYPE_TEXT
import de.osca.android.essentials.utils.constants.TYPE_DRAWABLE
import java.util.Locale

fun Context.shareText(title: String, text: String) {
    val sharingIntent = Intent(Intent.ACTION_SEND).apply {
        type = CONTENT_TYPE_TEXT
        putExtra(Intent.EXTRA_SUBJECT, title)
        putExtra(Intent.EXTRA_TEXT, text)
    }

    startActivity(
        Intent.createChooser(sharingIntent, getString(R.string.global_share_via))
    )
}

fun Context.openMapRouteTo(to: Coordinates) {
    val gmmIntentUri = Uri.parse("http://maps.google.com/maps?daddr=${to.latitude},${to.longitude}")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    startActivity(mapIntent)
}

fun Context.isLocationEnabled(): Boolean {
    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?
    return locationManager?.run {
        isProviderEnabled(LocationManager.GPS_PROVIDER) || isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } ?: false
}

/*** Suppress Missing permission because Android Studio accuses MissingPermission
even though the permission is added to the Manifest
 ***/
@SuppressLint("MissingPermission")
fun Context.getLastDeviceLocation(
    onCompleted: (LatLng?) -> Unit
) {
    val focusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isLocationEnabled()) {
                focusedLocationProviderClient.getLastLocation()
                focusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        val latLng = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                        onCompleted.invoke(latLng)
                    } else {
                        onCompleted.invoke(null)
                    }
                }
            }
        }
    }
}

/*** Suppress Missing permission because Android Studio accuses MissingPermission
even though the permission is added to the Manifest
 ***/
@SuppressLint("MissingPermission")
fun Context.getLastDeviceLocationOnce(
    onCompleted: (LatLng?) -> Unit
) {
    val focusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (isLocationEnabled()) {
                focusedLocationProviderClient.getLastLocation().addOnCompleteListener { task ->
                    val lastKnownLocation = task.result
                    if (lastKnownLocation != null) {
                        val latLng = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                        onCompleted.invoke(latLng)
                    } else {
                        onCompleted.invoke(null)
                    }
                }
            }
        }
    }
}

fun Context.metersToDistanceString(meters: Float): String {
    return if (meters > 1000f) {
        "${String.format(Locale.getDefault(), "%.1f", meters / 1000f)} " +
                this.getString(R.string.essentials_measure_kilometer)
    } else {
        "${String.format(Locale.getDefault(), "%.0f", meters)} " +
                this.getString(R.string.essentials_measure_meter)
    }
}

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.getDrawableByName(name: String?, @DrawableRes default: Int): Int {
    return try {
        resources.getIdentifier(name, TYPE_DRAWABLE, this.packageName)
    } catch (e: Resources.NotFoundException) {
        default
    }
}

fun Context.startDialIntent(phone: String) {
    try {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phone")
        this.startActivity(intent)
    } catch (e: Exception) {
    }
}

fun Context.startWebIntent(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent)
    } catch(ex: Exception) {

    }
}

fun Context.emailTo(email: String, subject: String) {
    try {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, subject)
        }
        startActivity(intent)
    } catch (e: Exception) {
    }
}

fun Context.shortToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
fun Context.longToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}


fun Context.booleanToLiteral(value: Boolean, literalType: BooleanLiteralType): String {
    return when (literalType) {
        BooleanLiteralType.YES_NO -> if (value) getString(R.string.global_yes) else getString(R.string.global_no)
        BooleanLiteralType.ON_OFF -> if (value) getString(R.string.global_on) else getString(R.string.global_off)
    }
}

/*** Suppress Missing permission because Android Studio accuses MissingPermission
even though the permission is added to the Manifest
 ***/
@SuppressLint("MissingPermission")
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}
