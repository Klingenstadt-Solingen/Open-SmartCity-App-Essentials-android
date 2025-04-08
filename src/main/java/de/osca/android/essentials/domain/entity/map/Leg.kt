package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.domain.entity.Coordinates

data class Leg(
    @SerializedName("distance")
    val distance: StringIntMap? = null,
    @SerializedName("duration")
    val duration: StringIntMap,
    @SerializedName("end_address")
    val endAddress: String? = null,
    @SerializedName("end_location")
    val endLocation: Coordinates? = null,
    @SerializedName("start_address")
    val startAddress: String,
    @SerializedName("start_location")
    val startLocation: Coordinates? = null,
    @SerializedName("steps")
    val steps: List<Step>? = null
)