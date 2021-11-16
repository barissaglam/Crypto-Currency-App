package barissaglam.cryptocurrencyapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import barissaglam.cryptocurrencyapp.databinding.ItemCoinBinding
import barissaglam.domain.model.Coin
class HomeAdapter(
    private val callback: HomeAdapterCallBack
) : ListAdapter<Coin, CoinViewHolder>(
    AdapterItemDiffCallback<Coin>()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CoinViewHolder(
            binding = ItemCoinBinding.inflate(layoutInflater, parent, false),
            callback = callback
        )
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class AdapterItemDiffCallback<T : Coin> : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }
    }
}
