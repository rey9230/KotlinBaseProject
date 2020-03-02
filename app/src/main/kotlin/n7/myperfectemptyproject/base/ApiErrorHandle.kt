package n7.myperfectemptyproject.base

import android.util.Log
import java.io.IOException
import java.net.SocketTimeoutException
import okhttp3.ResponseBody
import retrofit2.HttpException

/**
 * This class trace exceptions(api call or parse data or connection errors) &
 * depending on exception returns a [ErrorModel]
 *
 * */
object ApiErrorHandle {

    fun traceErrorException(throwable: Throwable?): ErrorModel {
        val errorModel: ErrorModel? = when (throwable) {
            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)
                if (throwable.code() == 401) {
                    ErrorModel(
                        throwable.message(),
                        throwable.code(),
                        ErrorModel.ErrorStatus.UNAUTHORIZED
                    )
                } else {
                    getHttpError(
                        throwable.response()?.errorBody()
                    )
                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                ErrorModel(
                    throwable.message,
                    ErrorModel.ErrorStatus.TIMEOUT
                )
            }

            // handle connection error
            is IOException -> {
                ErrorModel(
                    throwable.message,
                    ErrorModel.ErrorStatus.NO_CONNECTION
                )
            }
            else -> null
        }
        return errorModel ?: ErrorModel(
            "No Defined Error!",
            0,
            ErrorModel.ErrorStatus.BAD_RESPONSE
        )
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            val result = body?.string()
            Log.d("getHttpError", "getErrorMessage() called with: errorBody = [$result]")
            ErrorModel(
                body.toString(),
                400,
                ErrorModel.ErrorStatus.BAD_RESPONSE
            )
        } catch (e: Throwable) {
            ErrorModel(
                message = e.message,
                errorStatus = ErrorModel.ErrorStatus.NOT_DEFINED
            )
        }
    }
}

/**
 * This class designed to show different types of errors through error status & message
 *
 * */
data class ErrorModel(
    val message: String?,
    val code: Int?,
    var errorStatus: ErrorStatus
) {
    constructor(errorStatus: ErrorStatus) : this(null, null, errorStatus)

    constructor(message: String?, errorStatus: ErrorStatus) : this(message, null, errorStatus)

    fun getErrorMessage(): String {
        return when (errorStatus) {
            ErrorStatus.NO_CONNECTION -> "No connection!"
            ErrorStatus.BAD_RESPONSE -> "Bad response!"
            ErrorStatus.TIMEOUT -> "Time out!"
            ErrorStatus.EMPTY_RESPONSE -> "Empty response!"
            ErrorStatus.NOT_DEFINED -> "Not defined!"
            ErrorStatus.UNAUTHORIZED -> "Unauthorized!"
        }
    }

    /**
     * various error status to know what happened if something goes wrong with a repository call
     */
    enum class ErrorStatus {
        /**
         * error in connecting to repository (Server or Database)
         */
        NO_CONNECTION,
        /**
         * error in getting value (Json Error, Server Error, etc)
         */
        BAD_RESPONSE,
        /**
         * Time out  error
         */
        TIMEOUT,
        /**
         * no data available in repository
         */
        EMPTY_RESPONSE,
        /**
         * an unexpected error
         */
        NOT_DEFINED,
        /**
         * bad credential
         */
        UNAUTHORIZED
    }
}
