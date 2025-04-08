package de.osca.android.essentials.domain.entity

import android.util.Log
import com.google.gson.annotations.SerializedName
import de.osca.android.essentials.utils.extensions.toApiString
import de.osca.android.essentials.utils.extensions.toLocalDateTime
import java.io.Serializable
import java.time.LocalDateTime

data class DateEnvelope(
    @SerializedName("__type")
    val type: String? = null,
    @SerializedName("iso")
    val value: String? = null
) : Serializable {
    fun toLocalDateTime(): LocalDateTime? {
        return value?.run {
            when (type) {
                TYPE_DATE -> useApiDateFormat()
                else -> {
                    Log.d(
                        "DATAENVELOPE",
                        "An implementation for converting ($type) -> LocalDateTime wasn't specified."
                    )
                    null
                }
            }
        }
    }

    private fun useApiDateFormat(): LocalDateTime? {
        return try {
            value?.toLocalDateTime()
        } catch (e: Exception) {
            Log.d("DATAENVELOPE", "DateEnvelope - Error trying to parse ApiDateFormat")
            null
        }
    }

    companion object {
        const val TYPE_DATE = "Date"

        fun fromLocalDateTime(localDateTime: LocalDateTime): DateEnvelope {
            val isoDate = localDateTime.toApiString() ?: ""
            return DateEnvelope(
                type = TYPE_DATE,
                value = isoDate
            )
        }
    }
}