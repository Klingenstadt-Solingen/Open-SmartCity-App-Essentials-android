package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.domain.entity.Coordinates

data class Bounds(
    @SerializedName("northeast")
    val northeast: Coordinates? = null,
    @SerializedName("southwest")
    val southwest: Coordinates? = null
)
