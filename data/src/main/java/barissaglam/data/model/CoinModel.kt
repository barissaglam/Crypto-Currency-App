package barissaglam.data.model

import com.squareup.moshi.Json

class CoinModel(

    @field:Json(name = "uuid")
    val uuid: String? = null,

    @field:Json(name = "symbol")
    val symbol: String? = null,

    @field:Json(name = "name")
    val name: String? = null,

    @field:Json(name = "description")
    val description: String? = null,

    @field:Json(name = "color")
    val color: String? = null,

    @field:Json(name = "iconUrl")
    val iconUrl: String? = null,

    @field:Json(name = "websiteUrl")
    val websiteUrl: String? = null,

    @field:Json(name = "supply")
    val supply: SupplyModel? = null,

    @field:Json(name = "marketCap")
    val marketCap: String? = null,

    @field:Json(name = "price")
    val price: String? = null,

    @field:Json(name = "btcPrice")
    val btcPrice: String? = null,

    @field:Json(name = "listedAt")
    val listedAt: Long? = null,

    @field:Json(name = "change")
    val change: String? = null,

    @field:Json(name = "rank")
    val rank: Int? = null,

    @field:Json(name = "sparkline")
    val sparkline: List<String>? = null,

    @field:Json(name = "allTimeHigh")
    val allTimeHigh: AllTimeHighModel? = null,

    @field:Json(name = "coinrankingUrl")
    val coinRankingUrl: String? = null,

    @field:Json(name = "24hVolume")
    val volume24h: String? = null,
)
