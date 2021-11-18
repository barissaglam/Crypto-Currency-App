package barissaglam.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import kotlin.math.ln
import kotlin.math.pow

fun Int?.orZero(): Int = this ?: 0

fun Long?.orZero(): Long = this ?: 0L

fun Double?.orZero(): Double = this ?: 0.0

fun Double.isPositive(): Boolean = this > 0

fun BigDecimal.toCurrency(locale: Locale = Locale.US): String =
    NumberFormat.getCurrencyInstance(locale).run {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
        format(this@toCurrency)
    }

fun Double.toPercentText(): String = "${String.format("%.2f", this)} %"

fun BigDecimal.formatWithSuffix(): String = withSuffix(this.toLong())

fun BigDecimal.toCurrencyWithSuffix(): String = withSuffix(this.toLong())

@Suppress("MagicNumber")
private fun withSuffix(count: Long): String {
    if (count < 1000) return "" + count
    val exp = (ln(count.toDouble()) / ln(1000.0)).toInt()
    return String.format(
        "%.1f %c",
        count / 1000.0.pow(exp.toDouble()),
        "kMGTPE"[exp - 1]
    )
}
