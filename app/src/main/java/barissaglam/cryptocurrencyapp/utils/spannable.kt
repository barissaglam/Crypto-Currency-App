package barissaglam.cryptocurrencyapp.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

fun spannable(func: () -> SpannableString) = func()
private fun span(s: CharSequence, o: Any) = (
    if (s is String) SpannableString(s) else s as? SpannableString
        ?: SpannableString("")
    ).apply { setSpan(o, 0, length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) }

operator fun SpannableString.plus(s: SpannableString) = SpannableString(TextUtils.concat(this, s))
operator fun SpannableString.plus(s: String) = SpannableString(TextUtils.concat(this, s))

fun bold(s: CharSequence) = span(s, StyleSpan(android.graphics.Typeface.BOLD))
fun normal(s: CharSequence) = span(s, StyleSpan(android.graphics.Typeface.NORMAL))
fun color(color: Int, s: CharSequence) = span(s, ForegroundColorSpan(color))
