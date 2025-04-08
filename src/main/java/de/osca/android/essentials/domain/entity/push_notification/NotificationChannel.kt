package de.osca.android.essentials.domain.entity.push_notification

sealed class NotificationChannel(val name: String) {
    object ConstructionSites : NotificationChannel(CHANNEL_CONSTRUCTION_NAME)
    object PressReleases : NotificationChannel(CHANNEL_PRESS_RELEASES_NAME)
    object Corona : NotificationChannel(CHANNEL_CORONA_NAME)
    object Waste : NotificationChannel(CHANNEL_WASTE)

    object Default : NotificationChannel(CHANNEL_DEFAULT_NAME)

    companion object {
        const val CHANNEL_CONSTRUCTION_NAME = "baustellen-android"
        const val CHANNEL_PRESS_RELEASES_NAME = "meldungen-android"
        const val CHANNEL_WASTE = "abfall-android"
        const val CHANNEL_CORONA_NAME = "coronastats-android"
        const val CHANNEL_DEFAULT_NAME = "default-android"
    }
}

//TODO: ChangeName
interface NotificationChannelData {
    val id: String
    val nameResource: Int
}