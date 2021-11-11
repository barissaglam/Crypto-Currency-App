package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.domain.model.Coin
import barissaglam.domain.model.CoinsData

data class HomeViewState(
    val coinData: CoinsData
) {
    fun getItems(): List<Coin> {
        return coinData.coins
    }
}
