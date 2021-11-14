package barissaglam.cryptocurrencyapp.ui.detail

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import androidx.core.content.ContextCompat
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.ui.detail.data.TimePeriod
import barissaglam.cryptocurrencyapp.utils.bold
import barissaglam.cryptocurrencyapp.utils.color
import barissaglam.cryptocurrencyapp.utils.normal
import barissaglam.cryptocurrencyapp.utils.plus
import barissaglam.cryptocurrencyapp.utils.spannable
import barissaglam.domain.model.Coin
import barissaglam.extensions.formatWithSuffix
import barissaglam.extensions.isPositive
import barissaglam.extensions.toCurrency
import barissaglam.extensions.toCurrencyWithSuffix
import barissaglam.extensions.toHtml
import barissaglam.extensions.toPercentText
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

private const val LABEL = "Price"
private const val RIGHT_PARENTHESES = ")"
private const val LEFT_PARENTHESES = " ("
private const val ANALYTICS_SEPARATOR = " - "
private const val LINE_WIDTH = 2F

data class CoinDetailViewState(
    val coin: Coin,
    val timePeriod: TimePeriod = TimePeriod.DAILY
) {
    fun getLineData(context: Context): LineData {
        val entries = coin.sparkline.mapIndexed { index, sparkLine ->
            Entry(index.toFloat(), sparkLine.toFloat())
        }

        return LineDataSet(entries, LABEL).apply {
            color = getColor(context)
            mode = LineDataSet.Mode.CUBIC_BEZIER
            setDrawFilled(true)
            fillDrawable = getChartBackground(context)
            setDrawCircles(false)
            lineWidth = LINE_WIDTH
        }.let { lineDataSet ->
            LineData(lineDataSet).apply {
                setDrawValues(false)
            }
        }
    }

    private fun getChartBackground(context: Context): Drawable? {
        return if (coin.change.isPositive()) {
            ContextCompat.getDrawable(context, R.drawable.background_chart_up)
        } else {
            ContextCompat.getDrawable(context, R.drawable.background_chart_down)
        }
    }

    fun getColor(context: Context): Int {
        return if (coin.change.isPositive()) {
            ContextCompat.getColor(context, R.color.up_green)
        } else {
            ContextCompat.getColor(context, R.color.down_red)
        }
    }

    fun getChangeIcon(context: Context): Drawable? {
        return if (coin.change.isPositive()) {
            ContextCompat.getDrawable(context, R.drawable.ic_green)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_red)
        }
    }

    fun getToolbarTitle(): SpannableString {
        return spannable {
            bold(coin.name) + normal(LEFT_PARENTHESES + coin.symbol + RIGHT_PARENTHESES)
        }
    }

    fun getChangeText(): String {
        return coin.change.toPercentText()
    }

    fun getDescription(): String {
        return coin.description.toHtml()
    }

    fun getPriceText(): String {
        return coin.price.toCurrency()
    }

    fun getStaticsText(context: Context): SpannableString {
        val color = ContextCompat.getColor(context, R.color.color_text_main)
        return spannable {
            normal(context.getString(R.string.title_market_cap))
                .plus(color(color, bold(coin.marketCap.toCurrencyWithSuffix())))
                .plus(ANALYTICS_SEPARATOR)
                .plus(normal(context.getString(R.string.title_volume)))
                .plus(color(color, bold(coin.volume24h.toCurrencyWithSuffix())))
                .plus(ANALYTICS_SEPARATOR)
                .plus(normal(context.getString(R.string.title_available_supply)))
                .plus(color(color, bold(coin.supply.circulating.formatWithSuffix())))
                .plus(ANALYTICS_SEPARATOR)
                .plus(normal(context.getString(R.string.title_total_supply)))
                .plus(color(color, bold(coin.supply.total.formatWithSuffix())))
        }
    }

    fun isChip24hChecked(): Boolean {
        return timePeriod == TimePeriod.DAILY
    }

    fun isChip7dChecked(): Boolean {
        return timePeriod == TimePeriod.WEEKLY
    }

    fun isChip30dChecked(): Boolean {
        return timePeriod == TimePeriod.MONTHLY
    }
}
