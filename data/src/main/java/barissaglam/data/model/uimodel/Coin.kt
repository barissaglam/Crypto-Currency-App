package barissaglam.data.model.uimodel

import android.os.Parcelable
import barissaglam.core.adapter.AdapterItem
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Coin(
    override val uuid: String,
    val symbol: String,
    val name: String,
    val description: String,
    val color: String,
    val iconUrl: String,
    val supply: Supply,
    val marketCap: BigDecimal,
    val price: BigDecimal,
    val btcPrice: BigDecimal,
    val listedAt: Long,
    val change: Double,
    val rank: Int,
    val sparkline: List<BigDecimal>,
    val allTimeHigh: AllTimeHigh,
    val coinRankingUrl: String,
    val volume24h: BigDecimal,
) : Parcelable, AdapterItem
