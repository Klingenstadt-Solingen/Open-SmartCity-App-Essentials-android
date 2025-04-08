package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName

data class Route(
    @SerializedName("bounds")
    val bounds: Bounds? = null,
    @SerializedName("copyrights")
    val copyrights: String? = null,
    @SerializedName("legs")
    val legs: List<Leg>? = null,
    @SerializedName("overview_polyline")
    val overviewPolyline: Polyline? = null,
    @SerializedName("summary")
    val summary: String? = null,
    @SerializedName("warnings")
    val warnings: List<Any>? = null,
    @SerializedName("waypoint_order")
    val waypointOrder: List<Any>? = null
)