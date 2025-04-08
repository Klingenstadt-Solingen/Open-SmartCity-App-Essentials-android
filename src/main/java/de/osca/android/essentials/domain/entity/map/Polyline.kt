package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName

data class Polyline(
    @SerializedName("points")
    val points: String? = null
) {
}