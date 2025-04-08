package de.osca.android.essentials.domain.entity.server

import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannel

data class ParseInstallation(
    @SerializedName("objectId")
    val objectId: String? = null,
    @SerializedName("deviceType")
    val deviceType: String? = DEVICE_TYPE,
    @SerializedName("pushType")
    val pushType: String? = PUSH_TYPE,
    @SerializedName("channels")
    val channels: List<String> = emptyList(),
    @SerializedName("installationId")
    val installationId: String? = null,
    @SerializedName("deviceToken")
    val deviceToken: String? = null,
    @SerializedName("createdAt")
    val createdAt: String? = null,
    @SerializedName("updatedAt")
    val updatedAt: String? = null,
) {

    val notificationChannels get() = channels.map { channelName ->
        when (channelName) {
            NotificationChannel.CHANNEL_CONSTRUCTION_NAME -> NotificationChannel.ConstructionSites
            NotificationChannel.CHANNEL_CORONA_NAME -> NotificationChannel.Corona
            NotificationChannel.CHANNEL_PRESS_RELEASES_NAME -> NotificationChannel.PressReleases
            else -> NotificationChannel.Default
        }
    }

    fun isSubscribedTo(notificationChannel: NotificationChannel): Boolean {
        return channels.contains(notificationChannel.name)
    }

    companion object {
        const val DEVICE_TYPE = "android"
        const val PUSH_TYPE = "gcm"
    }
}
