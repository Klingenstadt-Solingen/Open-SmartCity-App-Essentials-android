package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName

data class ImageObject(
    @SerializedName("__type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)