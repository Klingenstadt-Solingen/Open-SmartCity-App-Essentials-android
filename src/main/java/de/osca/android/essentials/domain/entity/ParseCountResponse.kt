package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName

data class ParseCountResponse(
    @SerializedName("count")
    val count: Int?,
)
