package barissaglam.cryptocurrencyapp.ui.home.adapter

import barissaglam.core.adapter.BaseViewHolder
import barissaglam.cryptocurrencyapp.databinding.ItemCoinBinding
import barissaglam.cryptocurrencyapp.ui.home.CoinItemViewState
import barissaglam.data.model.uimodel.Coin

class CoinViewHolder(
    private val binding: ItemCoinBinding,
    private val callback: HomeAdapterCallBack
) : BaseViewHolder<Coin>(binding.root) {

    override fun bind(data: Coin) {
        binding.viewState = CoinItemViewState(data)

        binding.root.setOnClickListener {
            callback.onItemClick(data)
        }
    }
}
