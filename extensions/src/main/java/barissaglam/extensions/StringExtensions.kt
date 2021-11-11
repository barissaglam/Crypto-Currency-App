package barissaglam.extensions

import android.graphics.Color
import android.os.Build
import android.text.Html
import java.math.BigDecimal

const val EMPTY_STRING = ""
const val BLANK_STRING = " "

fun String?.toBigDecimalOrZero(): BigDecimal {
    return this?.toBigDecimalOrNull() ?: BigDecimal(0)
}

fun String?.toDoubleOrZero(): Double {
    return this?.toDoubleOrNull() ?: 0.0
}

fun String?.asColor(): Int {
    return try {
        Color.parseColor(this)
    } catch (e: Exception) {
        Color.BLUE
    }
}

fun String.toHtml(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }
}
