package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.domain.entity.Coordinates

data class Step(
    @SerializedName("distance")
    val distance: StringIntMap? = null,
    @SerializedName("duration")
    val duration: StringIntMap? = null,
    @SerializedName("end_location")
    val endLocation: Coordinates? = null,
    @SerializedName("html_instructions")
    val htmlInstructions: String? = null,
    @SerializedName("polyline")
    val polyline: Polyline? = null,
    @SerializedName("start_location")
    val startLocation: Coordinates? = null,
    @SerializedName("travel_mode")
    val travelMode: String? = null,
    @SerializedName("maneuver")
    val maneuver: String? = null
)