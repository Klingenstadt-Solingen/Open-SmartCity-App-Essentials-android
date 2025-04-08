package de.osca.android.essentials.data.storage

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import de.osca.android.essentials.domain.boundaries.EssentialsStorage
import de.osca.android.essentials.domain.entity.android.AppProperties
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannelData
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EssentialsStorageImpl @Inject constructor(
    @ApplicationContext val context: Context,
    private val properties: AppProperties
) : EssentialsStorage {
    override suspend fun getIsFirstStart(): Boolean {
        return context.storage.data
            .map { preferences -> preferences[ESSENTIALS_IS_FIRST_START] }
            .firstOrNull() ?: true
    }

    override suspend fun saveIsFirstStart(value: Boolean) {
        context.storage.edit { preferences ->
            preferences[ESSENTIALS_IS_FIRST_START] = value
        }
    }

    override suspend fun getWasUpdated(): Boolean {
        return context.storage.data
            .map { preferences -> preferences[ESSENTIALS_WAS_UPDATED] }
            .firstOrNull() ?: true
    }

    override suspend fun saveWasUpdated(value: Boolean) {
        context.storage.edit { preferences ->
            preferences[ESSENTIALS_WAS_UPDATED] = value
        }
    }

    override suspend fun getInstallationObjectId(): String? {
        return context.storage.data
            .map { preferences -> preferences[ESSENTIALS_PARSE_INSTALLATION_OBJECT_ID] }
            .firstOrNull()
    }

    override suspend fun saveInstallationObjectId(value: String) {
        context.storage.edit { preferences ->
            preferences[ESSENTIALS_PARSE_INSTALLATION_OBJECT_ID] = value
        }
    }

    override suspend fun getFCMToken(): String? {
        return context.storage.data
            .map { preferences -> preferences[ESSENTIALS_FCM_TOKEN] }
            .firstOrNull()
    }

    override suspend fun saveFCMToken(value: String) {
        context.storage.edit { preferences ->
            preferences[ESSENTIALS_FCM_TOKEN] = value
        }
    }

    override suspend fun getSubscribedNotificationChannels(): List<NotificationChannelData> {
        val allChannels = properties.availableNotificationChannels

        return context.storage.data
            .map { preferences -> preferences[NOTIFICATION_CHANNELS] }
            .firstOrNull()
            ?.toList()
            ?.mapNotNull { subscribedChannelName ->
                allChannels.firstOrNull { it.id == subscribedChannelName }
            } ?: emptyList()
    }

    override suspend fun setNotificationChannel(
        channel: NotificationChannelData,
        subscribe: Boolean
    ) {
        val availableChannels = properties.availableNotificationChannels
        val subscribed = getSubscribedNotificationChannels().map { it.id }.toMutableList()

        if (!availableChannels.contains(channel)) {
            Log.e(
                "EssentialsStorageImpl",
                "The channel ${channel.id} wasn't found on the available notification " +
                        "channels list. Please check your AppProperties/OSCAProperties."
            )
            return
        }

        if (subscribe && availableChannels.contains(channel)) {
            subscribed.add(channel.id)
        } else {
            subscribed.remove(channel.id)
        }

        context.storage.edit{ preferences ->
            preferences[NOTIFICATION_CHANNELS] = subscribed.toSet()
        }
    }

    companion object {
        const val ESSENTIALS_PREFERENCES_NAME = "essential_preferences"

        val ESSENTIALS_IS_FIRST_START = booleanPreferencesKey("essential_is_first_start")
        val ESSENTIALS_WAS_UPDATED = booleanPreferencesKey("essential_wasUpdated")
        val ESSENTIALS_PARSE_INSTALLATION_OBJECT_ID =
            stringPreferencesKey("parse_installation_object_id")
        val ESSENTIALS_FCM_TOKEN = stringPreferencesKey("essentials_fcm_token")

        val DASHBOARD_CONFIG = booleanPreferencesKey("dashboardConfig")

        val NOTIFICATION_CHANNELS = stringSetPreferencesKey("notification_channels")


        private val Context.storage: DataStore<Preferences> by preferencesDataStore(
            ESSENTIALS_PREFERENCES_NAME
        )
    }
}