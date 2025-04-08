package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName

data class DirectionsData(
    @SerializedName("geocoded_waypoints")
    val geocodedWayPoints: List<GeocodedWayPoints>? = listOf(),
    @SerializedName("routes")
    val routes: List<Route>? = listOf(),
    @SerializedName("status")
    val status: String? = null
)
