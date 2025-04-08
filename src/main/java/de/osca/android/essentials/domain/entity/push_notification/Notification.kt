package de.osca.android.essentials.domain.entity.push_notification

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName(NAMED_FIELD_TITLE)
    val title: String? = null,
    @SerializedName(NAMED_FIELD_MESSAGE)
    val message: String? = null,
    @SerializedName(NAMED_FIELD_URI)
    val uri: String? = null,
    @SerializedName(NAMED_FIELD_ID)
    val id: String? = null,
    @SerializedName(NAMED_FIELD_CHANNEL)
    val channel: String? = null,
) {
    companion object {
        const val NAMED_FIELD_MESSAGE = "alert"
        const val NAMED_FIELD_TITLE = "title"
        const val NAMED_FIELD_URI = "uri"
        const val NAMED_FIELD_ID = "id"
        const val NAMED_FIELD_CHANNEL = "channel"
    }
}