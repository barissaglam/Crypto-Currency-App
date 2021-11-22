package barissaglam.core.domain

import android.content.Context
import barissaglam.core.R
import java.io.IOException
import java.net.SocketTimeoutException

class ErrorPageViewState(
    val throwable: Throwable,
    val retryFunc: () -> Unit
) {

    fun getExceptionMessage(context: Context): String {
        return when (throwable) {
            is SocketTimeoutException -> context.getString(R.string.error_connection_timeout)
            is IOException -> context.getString(R.string.error_connection_not_found)
            is InvalidParamsException -> context.getString(R.string.error_empty_param)
            else -> context.getString(R.string.error_unknown)
        }
    }
}