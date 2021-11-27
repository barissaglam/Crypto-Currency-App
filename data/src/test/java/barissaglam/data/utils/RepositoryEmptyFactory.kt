package barissaglam.data.utils

import barissaglam.core.data.BaseResponse
import barissaglam.data.model.AllTimeHighModel
import barissaglam.data.model.CoinDetailDataModel
import barissaglam.data.model.CoinModel
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.model.StatsModel
import barissaglam.data.model.SupplyModel

object RepositoryEmptyFactory {

    fun getCoinsResponse(): BaseResponse<CoinsDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinsDataModel()
    )

    fun getCoinDetailResponse(): BaseResponse<CoinDetailDataModel> = BaseResponse(
        status = "test_status",
        data = getCoinDetailModel()
    )

    fun getCoinsDataModel() = CoinsDataModel(
        stats = getStatsModel(),
        coins = emptyList()
    )

    fun getStatsModel() = StatsModel(
        total = 0,
        totalCoins = 0,
        totalMarkets = 0,
        totalExchanges = 0,
        totalMarketCap = "",
        total24hVolume = ""
    )

    fun getCoinModel() = CoinModel(
        uuid = "",
        symbol = "",
        name = "",
        description = "",
        color = "",
        iconUrl = "",
        websiteUrl = "",
        supply = getSupplyModel(),
        marketCap = "",
        price = "",
        btcPrice = "",
        listedAt = 0,
        change = "",
        rank = 0,
        sparkline = emptyList(),
        allTimeHigh = getAllTimeHigh(),
        coinRankingUrl = "",
        volume24h = ""
    )

    fun getSupplyModel() = SupplyModel(
        confirmed = false,
        "",
        ""
    )

    fun getAllTimeHigh() = AllTimeHighModel(
        price = "",
        timestamp = 0
    )

    fun getCoinDetailModel() = CoinDetailDataModel(
        coin = getCoinModel()
    )
}
