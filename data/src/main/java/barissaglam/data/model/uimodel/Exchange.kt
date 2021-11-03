package barissaglam.data.model.uimodel

import barissaglam.core.adapter.AdapterItem
import java.math.BigDecimal

class Exchange(
    override val uuid: String,
    val rank: Int,
    val name: String,
    val iconUrl: String,
    val verified: Boolean,
    val recommended: Boolean,
    val numberOfMarkets: Int,
    val numberOfCoins: Int,
    val marketShare: Double,
    val coinRankingUrl: String,
    val volume24h: BigDecimal,
) : AdapterItem
