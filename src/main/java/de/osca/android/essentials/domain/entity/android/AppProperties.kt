package de.osca.android.essentials.domain.entity.android

import com.google.android.gms.maps.model.LatLng
import de.osca.android.essentials.domain.entity.push_notification.NotificationChannelData
import de.osca.android.essentials.domain.entity.server.ParseInfo

interface AppProperties {
    val isMocked: Boolean
    val defaultLatLng: LatLng

    val coronaMapLink: String

    val parseInfo: ParseInfo

    val availableNotificationChannels: List<NotificationChannelData>
}