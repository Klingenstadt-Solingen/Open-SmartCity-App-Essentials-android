package de.osca.android.essentials.domain.entity.map

import com.google.gson.annotations.SerializedName

data class StringIntMap(
    @SerializedName("text")
    val text: String? = null,
    @SerializedName("value")
    val value: Int? = null
)