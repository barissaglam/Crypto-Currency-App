package barissaglam.core.extension

import android.content.Context
import barissaglam.core.R
import java.io.IOException
import java.net.SocketTimeoutException

fun Throwable.getErrorMessage(context: Context): String {
    return when (this) {
        is SocketTimeoutException -> context.getString(R.string.error_connection_timeout)
        is IOException -> context.getString(R.string.error_connection_not_found)
        else -> context.getString(R.string.error_unknown)
    }
}
