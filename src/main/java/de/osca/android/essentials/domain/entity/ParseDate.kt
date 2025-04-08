package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName

data class ParseDate (
    @SerializedName("__type")
    val type: String? = null,
    @SerializedName("iso")
    val iso: String? = null
)