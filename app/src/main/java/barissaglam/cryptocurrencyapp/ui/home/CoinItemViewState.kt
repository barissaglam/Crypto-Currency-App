package barissaglam.cryptocurrencyapp.ui.home

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import barissaglam.cryptocurrencyapp.R
import barissaglam.data.model.uimodel.Coin
import barissaglam.extensions.isPositive
import barissaglam.extensions.toCurrency
import barissaglam.extensions.toPercentText

data class CoinItemViewState(val coin: Coin) {

    fun getChangeIcon(context: Context): Drawable? {
        return if (coin.change.isPositive()) {
            ContextCompat.getDrawable(context, R.drawable.ic_green)
        } else {
            ContextCompat.getDrawable(context, R.drawable.ic_red)
        }
    }

    fun getChangeText(): String {
        return coin.change.toPercentText()
    }

    fun getChangeTextColor(context: Context): Int {
        return if (coin.change.isPositive()) {
            ContextCompat.getColor(context, R.color.up_green)
        } else {
            ContextCompat.getColor(context, R.color.down_red)
        }
    }

    fun getPriceText(): String {
        return coin.price.toCurrency()
    }
}
