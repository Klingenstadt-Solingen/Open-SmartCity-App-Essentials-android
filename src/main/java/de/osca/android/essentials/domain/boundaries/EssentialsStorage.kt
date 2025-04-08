package de.osca.android.essentials.domain.boundaries

import de.osca.android.essentials.domain.entity.push_notification.NotificationChannelData

interface EssentialsStorage {
    suspend fun getIsFirstStart(): Boolean
    suspend fun saveIsFirstStart(value: Boolean)

    suspend fun getWasUpdated(): Boolean
    suspend fun saveWasUpdated(value: Boolean)
    suspend fun getInstallationObjectId(): String?
    suspend fun saveInstallationObjectId(value: String)

    suspend fun getFCMToken(): String?
    suspend fun saveFCMToken(value: String)

    suspend fun getSubscribedNotificationChannels(): List<NotificationChannelData>
    suspend fun setNotificationChannel(channel: NotificationChannelData, subscribe: Boolean = true)
}