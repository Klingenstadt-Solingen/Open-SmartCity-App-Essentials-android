package de.osca.android.essentials.domain.entity

import com.google.gson.annotations.SerializedName
import java.security.AuthProvider

data class User(
    @SerializedName("username")
    val username: String? = null,

    @SerializedName("authData")
    val authData: AuthProvider
)
