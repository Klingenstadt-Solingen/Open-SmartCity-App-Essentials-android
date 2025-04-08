package de.osca.android.essentials.domain.entity.error

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import java.io.Serializable
import java.util.ArrayList

data class ApiError(
    @SerializedName("message")
    val errorMessage: String? = null,
    @SerializedName("errors")
    val errors: ArrayList<String>? = null,
    @SerializedName("error")
    val error: String? = null,
    @SerializedName("code")
    val code: String? = null
) : Serializable, Throwable() {

    companion object {
        fun fromResponseBody(responseBody: ResponseBody?): ApiError? {
            return Gson().fromJson(responseBody?.string(), ApiError::class.java)
        }
    }
}