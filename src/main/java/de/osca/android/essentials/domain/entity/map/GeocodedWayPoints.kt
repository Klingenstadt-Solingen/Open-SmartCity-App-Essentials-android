package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName

data class GeocodedWayPoints(
    @SerializedName("geocoder_status")
    val geocoderStatus: String? = "",
    @SerializedName("place_id")
    val placeId: String? = "",
    @SerializedName("types")
    val types: List<String>? = listOf(),
)
