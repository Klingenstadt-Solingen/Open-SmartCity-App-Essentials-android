package de.osca.android.essentials.domain.entity.error

class RequestException private constructor(
    val errorCode: Int?,
    val errorMessage: String?,
    private val errorType: ErrorType,
    val throwable: Throwable?,
) : Exception() {
    override val message = super.message ?: errorMessage

    companion object {
        fun httpError(
            errorCode: Int,
            message: String? = null,
        ): RequestException {
            return RequestException(errorCode, message, ErrorType.HTTP, null)
        }

        fun networkError(exception: Throwable): RequestException {
            return RequestException(null, null, ErrorType.NETWORK, exception)
        }

        fun timeoutError(exception: Throwable): RequestException {
            return RequestException(null, null, ErrorType.TIMEOUT, exception)
        }

        fun unexpectedError(throwable: Throwable): RequestException {
            throwable.printStackTrace()
            return RequestException(null, throwable.message, ErrorType.UNEXPECTED, throwable)
        }
    }

    fun isHttpError(): Boolean {
        return errorType == ErrorType.HTTP
    }

    fun isNetworkError(): Boolean {
        return errorType == ErrorType.NETWORK
    }

    fun isUnexpectedError(): Boolean {
        return errorType == ErrorType.UNEXPECTED
    }

    fun isUnauthorizedError(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.UNAUTHORIZED
    }

    fun isTimeOutException(): Boolean {
        return errorType == ErrorType.TIMEOUT || HttpError.getErrorForCode(errorCode) == HttpError.TIMEOUT
    }

    fun isNotFoundException(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.NOT_FOUND
    }

    fun isUnProcessableEntity(): Boolean {
        return isHttpError() && HttpError.getErrorForCode(errorCode) == HttpError.UN_PROCESSABLE_ENTITY
    }
}
