package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName

data class ObjectCreationResponse(
    @SerializedName("objectId")
    var objectId: String? = null,
    @SerializedName("createdAt")
    var createdAt: String? = null
) {
    val isSuccessful get() = (objectId != null)
}