package barissaglam.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
class Stats(
    val total: Int,
    val totalCoins: Int,
    val totalMarkets: Int,
    val totalExchanges: Int,
    val totalMarketCap: BigDecimal,
    val total24hVolume: BigDecimal
) : Parcelable
