package de.osca.android.essentials.presentation.component.calendar

import android.content.Intent
import android.provider.CalendarContract
import de.osca.android.essentials.utils.constants.INTENT_TYPE_CALENDAR_EVENT
import de.osca.android.essentials.utils.extensions.toEpochMilli
import java.time.LocalDateTime
import java.time.ZoneId

fun AddEventToSystemCalendar(
    title: String,
    description: String = "",
    startDateTime: LocalDateTime,
    endDateTime: LocalDateTime? = null,
    isAllDay: Boolean = false
): Intent {
    val startDateTimeMillis = startDateTime.toEpochMilli()
    val endDateTimeMillis = endDateTime.toEpochMilli()

    return Intent(Intent.ACTION_EDIT).apply {
        type = INTENT_TYPE_CALENDAR_EVENT
        putExtra(CalendarContract.Events.TITLE, title)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startDateTimeMillis)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDateTimeMillis)
        putExtra(CalendarContract.Events.DESCRIPTION, description)
        putExtra(CalendarContract.Events.ALL_DAY, isAllDay)
    }
}