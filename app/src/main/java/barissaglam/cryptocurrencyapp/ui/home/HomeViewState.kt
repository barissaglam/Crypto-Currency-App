package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.data.model.uimodel.Coin
import barissaglam.data.model.uimodel.CoinsData

data class HomeViewState(
    val coinData: CoinsData
) {
    fun getItems(): List<Coin> {
        return coinData.coins
    }
}
