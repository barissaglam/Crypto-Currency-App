package barissaglam.data.model

import com.squareup.moshi.Json

class ExchangeModel(

    @field:Json(name = "uuid")
    val uuid: String? = null,

    @field:Json(name = "rank")
    val rank: Int? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "iconUrl")
    val iconUrl: String? = null,

    @field:Json(name = "verified")
    val verified: Boolean? = null,

    @field:Json(name = "recommended")
    val recommended: Boolean? = null,

    @field:Json(name = "numberOfMarkets")
    val numberOfMarkets: Int? = null,

    @field:Json(name = "numberOfCoins")
    val numberOfCoins: Int? = null,

    @field:Json(name = "marketShare")
    val marketShare: Double? = null,

    @field:Json(name = "coinrankingUrl")
    val coinRankingUrl: String? = null,

    @field:Json(name = "24hVolume")
    val volume24h: String? = null,
)
