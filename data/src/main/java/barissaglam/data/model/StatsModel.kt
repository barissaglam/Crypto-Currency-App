package barissaglam.data.model

import com.squareup.moshi.Json

class StatsModel(

    @field:Json(name = "total")
    val total: Int? = null,

    @field:Json(name = "totalCoins")
    val totalCoins: Int? = null,

    @field:Json(name = "totalMarkets")
    val totalMarkets: Int? = null,

    @field:Json(name = "totalExchanges")
    val totalExchanges: Int? = null,

    @field:Json(name = "totalMarketCap")
    val totalMarketCap: String? = null,

    @field:Json(name = "total24hVolume")
    val total24hVolume: String? = null,
)
