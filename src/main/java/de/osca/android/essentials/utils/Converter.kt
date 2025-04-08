package de.osca.android.essentials.utils

import android.util.Log
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinInstant
import kotlinx.datetime.toLocalDateTime
import java.lang.Exception
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime

object Converter {

    fun convertStringToUTCDateString(unformattedDate: String): String {
        return try {
            val localDateTime = convertStringToUTC(unformattedDate)

            String.format("%02d:%02d", localDateTime.hour, localDateTime.minute)
        } catch(ex: Exception) {
            "---"
        }
    }

    fun convertToUTCDateString(localDateTime: LocalDateTime): String {
        return try {
            String.format("%02d:%02d", localDateTime.hour, localDateTime.minute)
        } catch(ex: Exception) {
            "---"
        }
    }

    fun convertStringToUTC(unformattedDate: String): LocalDateTime {
        return try {
            val dateTime = Instant.parse(unformattedDate)

            dateTime.toKotlinInstant()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .toJavaLocalDateTime()
        } catch(ex: Exception) {
            LocalDateTime.now()
        }
    }

    fun getMinutesBetweenNowAndTime(localDateTime: LocalDateTime): Int {
        val diff: Duration = Duration.between(LocalDateTime.now(), localDateTime)

        return diff.toMinutes().toInt()
    }
}