package de.osca.android.essentials.domain.entity

import androidx.annotation.Keep
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName
import kotlin.math.*

@Keep
data class Coordinates(
    @SerializedName("__type")
    val type: String? = null,

    @SerializedName("latitude", alternate = ["lat"])
    var latitude: Double = 0.0,

    @SerializedName("longitude", alternate = ["lon", "lng"])
    var longitude: Double = 0.0,
) {
    fun toLatLng(): LatLng {
        return LatLng(latitude, longitude)
    }

    fun swapLatLong() {
        val oldLat = latitude
        latitude = longitude
        longitude = oldLat
    }

    // distance in meters of the points
    fun distanceTo(other: Coordinates?): Float {
        if(other != null) {
            return calcCrow(
                latitude.toFloat(),
                longitude.toFloat(),
                other.latitude.toFloat(),
                other.longitude.toFloat()
            ) * 1000.0f
        }

        return 0f
    }

    // in km
    private fun calcCrow(latA: Float, lonA: Float, latB: Float, lonB: Float): Float {
        val r = 6371.0f // km
        val dLat = toRad(latB - latA)
        val dLon = toRad(lonB - lonA)
        val lat1 = toRad(latA)
        val lat2 = toRad(latB)
        val a = sin(dLat / 2) * sin(dLat / 2) + sin(dLon / 2) * sin(dLon / 2) * cos(lat1) * cos(lat2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return round((r * c) * 10.0f) / 10.0f
    }
    private fun toRad(value: Float): Float {
        return (value * PI / 180.0f).toFloat()
    }

    companion object {
        const val TYPE_GEO_POINT = "GeoPoint"

        fun of(lat: Double, lng: Double): Coordinates {
            return Coordinates(
                type = TYPE_GEO_POINT,
                latitude = lat,
                longitude = lng
            )
        }

        fun getDefaultCoordinates(lat: Double, lng: Double): Coordinates {
            return Coordinates(
                type = TYPE_GEO_POINT,
                latitude = lat,
                longitude = lng
            )
        }
    }
}
