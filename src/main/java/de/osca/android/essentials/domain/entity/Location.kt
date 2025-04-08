package de.osca.android.essentials.domain.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Location(
    @SerializedName("id")
    var id: String = "",
    @SerializedName("address")
    var address: Address = Address(),
    @SerializedName("geopoint")
    var geopoint: Coordinates = Coordinates()
)
