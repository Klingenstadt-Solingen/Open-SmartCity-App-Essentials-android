package de.osca.android.essentials.domain.entity.server

import com.google.gson.annotations.SerializedName

data class ServerConfig(
    @SerializedName("params")
    val params: ServerParams = ServerParams()
)
