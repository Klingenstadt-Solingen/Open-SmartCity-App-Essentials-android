package de.osca.android.essentials.presentation.base

import android.util.Log
import de.osca.android.essentials.domain.entity.error.HttpError
import de.osca.android.essentials.domain.entity.error.RequestException
import de.osca.android.essentials.utils.strings.EssentialsStrings
import javax.inject.Inject
import javax.inject.Singleton



class DataErrorHandler @Inject constructor(
    private val essentialsStrings: EssentialsStrings
) {
    fun resolveMessage(throwable: Throwable): String {
        return when (throwable) {
            is RequestException -> resolveHttpErrorMessage(throwable)
            else -> essentialsStrings.errorUnknown
        }
    }

    private fun resolveHttpErrorMessage(
        exception: RequestException
    ): String {
        return when (HttpError.getErrorForCode(exception.errorCode)) {
            HttpError.NOT_FOUND -> essentialsStrings.errorNotFound
            HttpError.TIMEOUT -> essentialsStrings.errorSocketTimeout
            HttpError.INTERNAL_SERVER_ERROR -> essentialsStrings.errorUnknown
            else -> {
                essentialsStrings.errorUnknown
            }
        }
    }

    companion object {
        const val TAG = "HTTP_ERROR"
    }
}
