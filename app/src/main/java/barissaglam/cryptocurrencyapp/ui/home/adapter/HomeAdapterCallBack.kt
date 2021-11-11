package barissaglam.cryptocurrencyapp.ui.home.adapter

import barissaglam.domain.model.Coin

interface HomeAdapterCallBack {
    fun onItemClick(coin: Coin)
}
