package barissaglam.cryptocurrencyapp.ui.home.adapter

import barissaglam.data.model.uimodel.Coin

interface HomeAdapterCallBack {
    fun onItemClick(coin: Coin)
}
