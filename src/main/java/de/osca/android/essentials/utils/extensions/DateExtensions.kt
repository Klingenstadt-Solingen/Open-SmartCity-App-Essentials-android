package de.osca.android.essentials.utils.extensions

import de.osca.android.essentials.utils.constants.*
import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toLocalDateTime
import java.time.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.TextStyle
import java.time.temporal.Temporal
import java.util.*

fun LocalDate?.toDateString(): String? {
    val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    return this?.format(formatter)
}

fun LocalDateTime?.toDateString(): String? {
    val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    return this?.format(formatter)
}

fun LocalDateTime?.toDateTimeString(format: String = DATE_TIME_FORMAT): String? {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this?.format(formatter)
}

fun LocalDateTime?.toTimeString(format: String = TIME_FORMAT): String? {
    val formatter = DateTimeFormatter.ofPattern(format)
    return this?.format(formatter)
}

fun LocalDateTime?.toEpochMilli(): Long? {
    return this?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
}

fun String.toLocalDateTime(): LocalDateTime? {
    return try {
        val tz = TimeZone.currentSystemDefault()
        this.toInstant().toLocalDateTime(tz).toJavaLocalDateTime()
    } catch (e: DateTimeParseException) {
        null
    }
}

fun LocalDateTime?.fromUTCTo(zoneId: ZoneId = DEFAULT_ZONE_ID): LocalDateTime? {
    return this?.toInstant(ZoneOffset.UTC)?.atZone(zoneId)
        ?.toLocalDateTime()
}

fun LocalDateTime.toApiString(dateFormat: String = API_DATE_FORMAT): String? {
    val formatter = DateTimeFormatter.ofPattern(dateFormat)
    return this.format(formatter)
}

fun LocalDate.isToday(): Boolean {
    return this == LocalDate.now()
}

fun LocalDate.isTomorrow(): Boolean {
    return LocalDate.now().plusDays(1) == this
}

fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}

fun LocalDateTime.getWeekdayName(): String {
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMANY)
}

fun LocalDateTime.getWeekdayShortName(): String {
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.GERMANY).substring(0, 2)
}

fun LocalDateTime.getMonthName(): String {
    return month.getDisplayName(TextStyle.FULL, Locale.GERMANY)
}


fun LocalDateTime.diffTo(second: Temporal = LocalDateTime.now()): Duration? {
    return Duration.between(this, second)
}