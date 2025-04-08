package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("name")
    var name: String = "",
    @SerializedName("streetAddress")
    var streetAddress: String = "",
    @SerializedName("addressLocality")
    var addressLocality: String = "",
    @SerializedName("postalCode")
    var postalCode: String = ""
)